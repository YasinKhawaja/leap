import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { Capability } from '../../classes/capability/capability';
import { CapabilityService } from '../../services/capability/capability.service';
import html2canvas from 'html2canvas';
import { saveAs } from "file-saver";
import pptxgen from "pptxgenjs";
import { NavbarService } from 'src/app/services/navbar/navbar.service';
import { NgxPrintModule } from "ngx-print";
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

  constructor(private cs: CapabilityService, private ns: NavbarService, private print: NgxPrintModule) {
    this.capabilities = [];
  }

  ngOnInit(): void {
    let environmentId = this.ns.getEnvironment();
    //let environmentId = this.router.url.split('/')[2];

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
      slide.addImage({ data: contentDataURL, x: 0, y: 1.5, w: '100%', h:'100%'});

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
    this.ngOnInit();
  }
}
