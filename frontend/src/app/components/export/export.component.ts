import { ChangeDetectorRef, Component, OnInit } from '@angular/core';
import { waitForAsync } from '@angular/core/testing';
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
import Swal from 'sweetalert2';
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

  contentDataURL = null

  style: { 'border-color': string };
  filter: string;

  strategies: string[]

  parents: string[]

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
    this.strategies = [];
    this.strategyItems = [];
    this.capabilitiesLinkedToItApplication = [];
    this.capabilityStrategyItemsLinkedToStrategyItem = [];
    this.parents = [];
  }

  ngOnInit(): void {
    let environmentId = this.ns.getEnvironmentCookie();

    this.cs.getCapabilitiesWithParents(environmentId)
      .subscribe(
        (res) => {
          this.parents = res
          console.log(this.parents)
        },
        () => {
          Swal.fire('Error', 'failed to load parents of capabilities', 'error')
        }
      )

    this.cs.getAllCapabilitiesInEnvironment(environmentId)
      .subscribe(result => {
        this.capabilities = result;
        console.log(this.capabilities);
        this.capabilitiesLevel1 = this.capabilities.filter(capability => capability.level == '1')

        this.capabilitiesLevel2 = this.capabilities.filter(capability => capability.level == '2')

        this.capabilitiesLevel3 = this.capabilities.filter(capability => capability.level == '3')
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

  setITApplicationFilter() {
    this.filter = "ITApplication"; // set filter to ITApplication to apply the colors on the capability map for the IT application filter
    this.strategyItem.get("strategyItemName").reset(); // reset strategy item dropdown when IT application filter is clicked.  
  }

  // this method gets executed from the html of this component when the filter is ITApplication.
  // for every capability, the sum of its information quality and application fit is checked to determine which border-color the capability gets.
  applyITApplicationFilter(capability) {
    if ((Number(capability.informationQuality) + Number(capability.applicationFit)) != 0) {

      if ((Number(capability.informationQuality) + Number(capability.applicationFit)) < 5) {
        this.style = { 'border-color': 'red' }
      } else if ((Number(capability.informationQuality) + Number(capability.applicationFit)) > 8) {
        this.style = { 'border-color': 'green' }
      } else this.style = { 'border-color': 'orange' }
    }
    else this.style = { 'border-color': 'black' }
    return this.style;

  }

  // when a strategy is selected, it retrieves all its strategy items
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
    this.filter = "StrategyItem"; // when a strategy item is chosen, set the filter to StrategyItem to apply the colors on the capability map for this filter
    this.csis.getCapabilityStrategyItemsLinkedToStrategyItem(this.strategyItem.value.strategyItemName)
      .subscribe(result => {
        this.capabilityStrategyItemsLinkedToStrategyItem = result;

      },
        error => console.log(error))

  }

  // this method gets executed from the html of this component when the filter is set to StrategyItem
  // for every capability linked to the selected strategy item, the border-color of that capability is set based on the strategic emphasis
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
    var children: string[];
    var parents: string[];
    var count: number;

    children = [];
    parents = [];
    count = 0;

    for (var i = 0; i < this.parents.length; i++) {
      children.push(this.parents[i].split(';')[0])
      parents.push(this.parents[i].split(';')[1])
    }
    console.log(children)
    console.log(parents)

    for (var i = 0; i < this.capabilities.length; i++) {
      if (children.includes(cap[i].name)) {
        console.log(i + ": " + cap[i].name)
        cap[i].parent = parents[count];
        count++;
      }
    }
    console.log(cap);

    const replacer = (key, value) => value === null ? '' : value; // specify how you want to handle null values here

    const header = Object.keys(cap[0]); //gets all keys of capability to later be added
    header.push("parent"); //adds the parent header to the csv

    let csv = cap.map(row => header.map(
      fieldName =>
        JSON.stringify(row[fieldName], replacer)).join(';')) //adds values to row

    csv.unshift(header.join(';')) // adds headers to csv

    let csvArray = csv.join('\r\n'); // makes csv

    var blob = new Blob([csvArray], { type: 'text/csv' })
    saveAs(blob, "CapabilityMap.csv");
  }

  generatePowerPoint() {
    let powerpoint = new pptxgen();
    var counter: number = 0

    this.capabilitiesLevel1.forEach(element => {
      let slide = powerpoint.addSlide();
      let data = document.getElementById(element.id);
      html2canvas(data, { scrollY: -window.scrollY }).then(canvas => { // convert the capability map html to an image
        const contentDataURL = canvas.toDataURL('image/png', 4)
        slide.addImage({ data: contentDataURL, x: 0, y: 1.5, w: '100%', h: '50%' });// save powerpoint 
        counter++
        if (counter.valueOf() == this.capabilitiesLevel1.length) {
          powerpoint.writeFile({ fileName: "CapabilityMap" });
        }
      });
    })
  }

  generatePDF() {
    let doc = new jsPDF();

    // capture the capabity map which is located in the div with id 'divLeftHalf'
    let data = document.getElementById('divLeftHalf');
    html2canvas(data, { scrollY: -window.scrollY }).then(canvas => {
      const contentDataURL = canvas.toDataURL('image/png', 4);

      // determine if the pdf should be landscape or portrait 
      if (canvas.width > canvas.height) {
        doc = new jsPDF('l', 'mm', [canvas.width, canvas.height]);
      }
      else {
        doc = new jsPDF('p', 'mm', [canvas.height, canvas.width]);
      }

      doc.addImage(contentDataURL, 'png', 0, 0, canvas.width, canvas.height);

      // save powerpoint
      doc.save("CapabilityMap");
    });
  }

  // reload the capability map without any filters
  generateEntireMap() {
    window.location.reload();
  }
}
