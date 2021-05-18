import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { Capability } from '../../classes/capability/capability';
import { CapabilityService } from '../../services/capability/capability.service';
import { jsPDF } from "jspdf";
import html2canvas from 'html2canvas';
import { saveAs } from "file-saver";
import pptxgen from "pptxgenjs";
@Component({
  selector: 'app-export',
  templateUrl: './export.component.html',
  styleUrls: ['./export.component.css']
})
export class ExportComponent implements OnInit {

  capabilities: Capability[]

  constructor(private cs: CapabilityService) { 
    this.capabilities = [];
  }

  ngOnInit(): void {
    this.cs.getAllCapabilities()
               .subscribe(data => { this.capabilities = data }, 
                          error => { console.error(error) })
  }

  generatePDF() {
    let data = document.getElementById('pdf');  
        html2canvas(data , {
          width: 2000,
          height: 10000
        }).then(canvas => {
        const contentDataURL = canvas.toDataURL('image/png')
        console.log(canvas);
        let pdf = new jsPDF('l','mm','a4'); //Generates PDF in landscape mode
        // let pdf = new jspdf('p', 'cm', 'a4'); Generates PDF in portrait mode
        var imgWidth = 300;
        // imgHeight nog beter aanpassen
        // de canvas wordt niet helemaal meegenomen als er capabilities bijkomen waardoor de map groter wordt
        var imgHeight = canvas.height * imgWidth / canvas.width;
        pdf.addImage(contentDataURL, 'PNG', 0, 0, imgWidth, imgHeight);  
        pdf.save('CapabilityMap.pdf');
        console.log(canvas);
      }); 
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
          width: 1500,
          height: 1500,
          scale: 4
        }).then(canvas => {
        const contentDataURL = canvas.toDataURL('image/png')
    slide.addImage({data: contentDataURL, x: 1, y: 1,sizing:{type:'cover', w:5, h:4}});

    // save powerpoint
    powerpoint.writeFile({fileName: "CapabilityMap"});
  });
  }
}
