import { ChangeDetectorRef, Component, OnInit } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { saveAs } from "file-saver";
import html2canvas from 'html2canvas';
import { jsPDF } from "jspdf";
import { NgxPrintModule } from "ngx-print";
import pptxgen from "pptxgenjs";
import { CapabilityStrategyItems } from 'src/app/classes/capability-strategyitems/capability-strategyitems';
import { CapabilityApplicationService } from 'src/app/services/capability-application/capability-application.service';
import { CapabilityStrategyitemService } from 'src/app/services/capability-strategyitem/capability-strategyitem.service';
import { ItapplicationService } from 'src/app/services/itapplication/itapplication.service';
import { NavbarService } from 'src/app/services/navbar/navbar.service';
import { StrategyItemService } from 'src/app/services/strategy-item/strategy-item.service';
import { StrategyService } from 'src/app/services/strategy/strategy.service';
import { Capability } from '../../classes/capability/capability';
import { CapabilityService } from '../../services/capability/capability.service';

@Component({
  selector: 'app-export',
  templateUrl: './export.component.html',
  styleUrls: ['./export.component.css']
})
export class ExportComponent implements OnInit {

  capabilities: Capability[]
  capabilitiesLevel1: Capability[]
  capabilitiesLevel2: Capability[]
  capabilitiesLevel3: Capability[]

  capabilitiesLinkedToItApplication: Capability[]
  capabilityStrategyItemsLinkedToStrategyItem: CapabilityStrategyItems[]

  style: { 'border-color': string };
  filter: string;


  itApplications: string[]

  itApplication = this.fb.group({
    itApplicationName: ['']
  })

  strategies: string[]

  strategy = this.fb.group({
    strategyName: ['']
  })

  strategyItems: string[]

  strategyItem = this.fb.group({
    strategyItemName: ['']
  })

  constructor(private cs: CapabilityService, private ns: NavbarService, private print: NgxPrintModule, private fb: FormBuilder,
    private its: ItapplicationService, private sis: StrategyItemService, private strats: StrategyService, private cis: CapabilityApplicationService,
    private cd: ChangeDetectorRef, private csis: CapabilityStrategyitemService) {
    this.capabilities = [];
    this.itApplications = [];
    this.strategies = [];
    this.strategyItems = [];
    this.capabilitiesLinkedToItApplication = [];
    this.capabilityStrategyItemsLinkedToStrategyItem = [];
  }

  ngOnInit(): void {
    let environmentId = this.ns.getEnvironmentCookie();

    this.cs.getAllCapabilitiesInEnvironment(environmentId)
      .subscribe(result => {
        this.capabilities = result;
        this.capabilitiesLevel1 = this.capabilities.filter(capability => capability.level == '1')

        this.capabilitiesLevel2 = this.capabilities.filter(capability => capability.level == '2')

        this.capabilitiesLevel3 = this.capabilities.filter(capability => capability.level == '3')
      },
        error => console.log(error));

    this.itApplications = [];
    this.its.getITApplications_CurrentEnvironment(environmentId)
      .subscribe(result => {
        result.forEach(e => {
          this.itApplications.push(e.name);
        })
      },
        error => console.log(error));

    this.strats.getAllStrategyInEnvironment(environmentId)
      .subscribe(result => {
        result.forEach(e => {
          this.strategies.push(e.name);
        })
      },
        error => console.log(error));
  }

  changeITApplication() {
    this.filter = "ITApplication";
    this.cis.getCapabilitiesLinkedToITApplication(this.itApplication.value.itApplicationName)
      .subscribe(result => {
        this.capabilitiesLinkedToItApplication = result;
        this.strategyItem.get("strategyItemName").reset();
      },
        error => console.log(error))
  }

  applySelectedITApplication(capability) {
    for (var i = 0; i < this.capabilitiesLinkedToItApplication.length; i++) {
      if (this.capabilitiesLinkedToItApplication[i].id == capability.id) {
        if ((Number(capability.informationQuality) + Number(capability.applicationFit)) > 8) {
          this.style = { 'border-color': 'green' }
          break;
        } if ((Number(capability.informationQuality) + Number(capability.applicationFit)) > 5) {
          this.style = { 'border-color': 'orange' }
          break;
        }
        this.style = { 'border-color': 'red' }
        break;
      } else this.style = { 'border-color': 'black' }
    } return this.style;

  }

  changeStrategy() {
    this.strategyItems = [];

    this.sis.getAllStrategyItemsInStrategyByName(this.strategy.value.strategyName)
      .subscribe(result => {
        result.forEach(e => {

          this.strategyItems.push(e.name);
        })
      },
        error => console.log(error));
  }

  changeStrategyItem() {
    this.filter = "StrategyItem";
    this.csis.getCapabilityStrategyItemsLinkedToStrategyItem(this.strategyItem.value.strategyItemName)
      .subscribe(result => {
        this.capabilityStrategyItemsLinkedToStrategyItem = result;

        this.itApplication.get("itApplicationName").reset();
      },
        error => console.log(error))

  }

  applySelectedStrategyItem(capability) {
    for (var i = 0; i < this.capabilityStrategyItemsLinkedToStrategyItem.length; i++) {
      if (this.capabilityStrategyItemsLinkedToStrategyItem[i].capability.id == capability.id) {

        if (this.capabilityStrategyItemsLinkedToStrategyItem[i].strategicEmphasis == "HIGH") {
          this.style = { 'border-color': 'green' }
          break;
        } if (this.capabilityStrategyItemsLinkedToStrategyItem[i].strategicEmphasis == "MEDIUM") {
          this.style = { 'border-color': 'orange' }
          break;
        } if (this.capabilityStrategyItemsLinkedToStrategyItem[i].strategicEmphasis == "LOW") {
          this.style = { 'border-color': 'red' }
          break;
        } this.style = { 'border-color': 'black' }
        break;
      } else this.style = { 'border-color': 'black' };
    }
    return this.style;
  }

  generateCSV() {
    let cap = this.capabilities;
    const replacer = (key, value) => value === null ? '' : value; // specify how you want to handle null values here
    const header = Object.keys(cap[0]);
    let csv = cap.map(row => header.map(fieldName => JSON.stringify(row[fieldName], replacer)).join(';'));
    csv.unshift(header.join(';'));
    let csvArray = csv.join('\r\n');

    var blob = new Blob([csvArray], { type: 'text/csv' })
    saveAs(blob, "CapabilityMap.csv");
  }

  generatePowerPoint() {
    // create new powerpoint
    let powerpoint = new pptxgen();


    // add a slide
    let slide = powerpoint.addSlide();

    // add the capability map image

    let data = document.getElementById('divLeftHalf');
    html2canvas(data, { scrollY: -window.scrollY }).then(canvas => {
      const contentDataURL = canvas.toDataURL('image/png', 4)
      slide.addImage({ data: contentDataURL, x: 0, y: 0, w: '100%', h: '100%' });

      // save powerpoint
      powerpoint.writeFile({ fileName: "CapabilityMap" });
    });
  }

  generatePDF() {
    let doc = new jsPDF();
    
    let data = document.getElementById('divLeftHalf');
    html2canvas(data, {scrollY: -window.scrollY}).then(canvas => {
      const contentDataURL = canvas.toDataURL('image/png', 4);
      
      if(canvas.width > canvas.height){
        doc = new jsPDF('l', 'mm', [canvas.width, canvas.height]);
        }
        else{
        doc = new jsPDF('p', 'mm', [canvas.height, canvas.width]);
        }
      
      // let pdf = new jsPDF('p', 'mm', [canvas.height *2, canvas.width]);
       doc.addImage(contentDataURL, 'png', 0, 0, canvas.width, canvas.height);

      // save powerpoint
      doc.save( "CapabilityMap");
    });
    }

    generateLevel1Layer() {
      let capabilitiesLevel1: Capability[]
      capabilitiesLevel1 = this.capabilities.filter(cap => cap.level == '1');
      this.capabilities = capabilitiesLevel1;
    }

    generateLevel1and2Layer() {
      let capabilitiesLevel2: Capability[]
      capabilitiesLevel2 = this.capabilities.filter(cap => cap.level == '2' || cap.level == '1');
      this.capabilities = capabilitiesLevel2;
    }

    generateEntireMap() {
      window.location.reload();
    }
  }
