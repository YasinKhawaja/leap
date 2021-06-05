import { ChangeDetectorRef, Component, OnInit } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { saveAs } from "file-saver";
import html2canvas from 'html2canvas';
import { NgxPrintModule } from "ngx-print";
import pptxgen from "pptxgenjs";
import { CapabilityApplicationService } from 'src/app/services/capability-application/capability-application.service';
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

  style: { 'border-color': string };


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
    private cd: ChangeDetectorRef) {
    this.capabilities = [];
    this.itApplications = [];
    this.strategies = [];
    this.strategyItems = [];
    this.capabilitiesLinkedToItApplication = [];
  }

  ngOnInit(): void {
    let environmentId = this.ns.getEnvironment();

    this.cs.getAllCapabilitiesInEnvironment(environmentId)
      .subscribe(result => {
        this.capabilities = result;
        this.capabilitiesLevel1 = this.capabilities.filter(capability => capability.level == '1')
        console.log(this.capabilitiesLevel1)
        this.capabilitiesLevel2 = this.capabilities.filter(capability => capability.level == '2')
        console.log(this.capabilitiesLevel2)
        this.capabilitiesLevel3 = this.capabilities.filter(capability => capability.level == '3')
        console.log(this.capabilitiesLevel3)
        console.log(result);
      },
        error => console.log(error));

    this.itApplications = [];
    this.its.getITApplications_CurrentEnvironment(environmentId)
      .subscribe(result => {
        result.forEach(e => {
          this.itApplications.push(e.name);
        })
        console.log(result);
      },
        error => console.log(error));

    this.strats.getAllStrategyInEnvironment(environmentId)
      .subscribe(result => {
        result.forEach(e => {
          this.strategies.push(e.name);
        })
        console.log(result);
      },
        error => console.log(error));
  }

  changeITApplication() {
    console.log(this.itApplication.value.itApplicationName);
    this.cis.getCapabilitiesLinkedToITApplication(this.itApplication.value.itApplicationName)
      .subscribe(result => {
        this.capabilitiesLinkedToItApplication = result;
        console.log(this.capabilitiesLinkedToItApplication);
      },
        error => console.log(error))
  }

  applySelectedITApplication(capability) {
    for (var i = 0; i < this.capabilitiesLinkedToItApplication.length; i++) {
      if (this.capabilitiesLinkedToItApplication[i].name == capability.name) {
        this.style = { 'border-color': 'red' }
        break;
      } else this.style = { 'border-color': 'black' };
    } return this.style;

  }

  changeStrategy() {
    this.strategyItems = [];

    this.sis.getAllStrategyItemsInStrategyByName(this.strategy.value.strategyName)
      .subscribe(result => {
        result.forEach(e => {

          this.strategyItems.push(e.name);
        })
        console.log(result);
      },
        error => console.log(error));
  }

  changeStrategyItem() {
    this.strategyItem.value.strategyItemName;
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
    window.scrollTo(0, 0);
    // create new powerpoint
    let powerpoint = new pptxgen();


    // add a slide
    let slide = powerpoint.addSlide();

    // add object(s)
    let title = "Capability Map";
    slide.addText(title, { x: 0.5, y: 0.7, w: '100%', color: "0000FF", fontSize: 40 });

    // add the capability map image

    let data = document.getElementById('pdf');
    html2canvas(data).then(canvas => {
      const contentDataURL = canvas.toDataURL('image/png', 4)
      // w/h ratio 4/1 , w:20 h:5 was goed
      slide.addImage({ data: contentDataURL, x: 0, y: 1.5, w: '100%', h: '100%' });

      // save powerpoint
      powerpoint.writeFile({ fileName: "CapabilityMap" });
    });
  }

  generateLevel1Layer() {
    let capabilitiesLevel1: Capability[]
    capabilitiesLevel1 = this.capabilities.filter(cap => cap.level == '1');
    this.capabilities = capabilitiesLevel1;
    console.log(this.capabilities);
    console.log("button called");
  }

  generateLevel1and2Layer() {
    let capabilitiesLevel2: Capability[]
    capabilitiesLevel2 = this.capabilities.filter(cap => cap.level == '2' || cap.level == '1');
    this.capabilities = capabilitiesLevel2;
    console.log(this.capabilities);
    console.log("button called");
  }

  generateEntireMap() {
    window.location.reload();
  }
}
