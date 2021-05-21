import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { Capability } from '../../classes/capability/capability';
import { CapabilityService } from '../../services/capability/capability.service';
import { jsPDF } from "jspdf";
import html2canvas from 'html2canvas';
import { saveAs } from "file-saver";
import pptxgen from "pptxgenjs";
import { NavbarService } from 'src/app/services/navbar/navbar.service';
@Component({
  selector: 'app-export',
  templateUrl: './export.component.html',
  styleUrls: ['./export.component.css']
})
export class ExportComponent implements OnInit {

  capabilities: Capability[]
  
  constructor(private cs: CapabilityService, private ns: NavbarService) { 
    this.capabilities = [];
  }

  ngOnInit(): void {
    let environmentId = this.ns.getEnvironment();
    //let environmentId = this.router.url.split('/')[2];

    this.cs.getAllCapabilitiesInEnvironment(environmentId)
      .subscribe(result => {
        this.capabilities = result;
        console.log(result);
      },
      error => console.log(error));
  }
  

  generatePDF() {
        // let data = document.getElementById('pdf');  
        // html2canvas(data , {
        //   width: 2500,
        //   height: 15000
        // }).then(canvas => {
        // var contentWidth = canvas.width;
        // var contentHeight = canvas.height;

        // const contentDataURL = canvas.toDataURL('image/png', 10.0)
        // let pdf = new jsPDF(); //Generates PDF in landscape mode
        // // let pdf = new jspdf('p', 'cm', 'a4'); Generates PDF in portrait mode
        // var imgWidth = 500;
        // var imgHeight = 450/contentWidth * contentHeight;
        // // imgHeight nog beter aanpassen
        // // de canvas wordt niet helemaal meegenomen als er capabilities bijkomen waardoor de map groter wordt
        // pdf.addImage(contentDataURL, 'PNG', 200, -50, imgWidth, imgHeight);  
        // pdf.save('CapabilityMap.pdf');
        // console.log(canvas);
        // }); 

        // let data = document.getElementById('pdf');  
        // html2canvas(data , {
        //   width: 5000,
        //   height: 3200
        // }).then(canvas => {
        // const contentDataURL = canvas.toDataURL('image/png');
        // let pdf = new jsPDF();
        // pdf.addImage(contentDataURL, 'PNG', 200, -50, 500, 2000);
        // pdf.save('Test.pdf');})

        var w = document.getElementById("pdf").offsetWidth;
        var h = document.getElementById("pdf").offsetHeight;
        console.log(w + " " + h);
        let data = document.getElementById('pdf'); 
        html2canvas(data , {
            height: h * 3,
            width: 2500
           }).then(canvas => {
            var img = canvas.toDataURL("image/jpeg", 1);
            var doc = new jsPDF('l', 'px', [w, h]);
            doc.addImage(img, 'JPEG', 0, 0, w, h);
            doc.save('sample-file.pdf');})
  }

  generateCSV() {
    let cap = this.capabilities;
    const replacer = (key, value) => value === null ? '' : value; // specify how you want to handle null values here
    const header = Object.keys(cap[0]);
    let csv = cap.map(row => header.map(fieldName => JSON.stringify(row[fieldName], replacer)).join(';'));
    csv.unshift(header.join(';'));
    let csvArray = csv.join('\r\n');

    var blob = new Blob([csvArray], {type: 'text/csv' })
    saveAs(blob, "CapabilityMap.csv");
  }

  generatePowerPoint() {
    // create new powerpoint
    let powerpoint = new pptxgen();

    // add a slide
    let slide = powerpoint.addSlide();

    // add object(s)
    let title = "Capability Map";
    slide.addText(title, { x: 0.5, y: 0.7, w: '100%', color: "0000FF", fontSize: 40 });

    // add the capability map image
    let data = document.getElementById('pdf');  
        html2canvas(data , {
          width: 2000,
          height: 3200
        }).then(canvas => {
        const contentDataURL = canvas.toDataURL('image/png')
    slide.addImage({data: contentDataURL, x: 1, y: 1,sizing:{type:'contain', w:5, h:4}});
 
    // save powerpoint
    powerpoint.writeFile({fileName: "CapabilityMap"});
  });
  }

  generateLevel1Layer(){
    let capabilitiesLevel1: Capability[]
    capabilitiesLevel1 = this.capabilities.filter(cap => cap.level == '1');
    this.capabilities = capabilitiesLevel1;
    console.log(this.capabilities);
    console.log("button called");
  }

  generateLevel1and2Layer(){
    let capabilitiesLevel2: Capability[]
    capabilitiesLevel2 = this.capabilities.filter(cap => cap.level == '2' || cap.level == '1');
    this.capabilities = capabilitiesLevel2;
    console.log(this.capabilities);
    console.log("button called");
  }

  generateEntireMap(){
    this.ngOnInit();
  }
}
