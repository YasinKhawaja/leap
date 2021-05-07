import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { Capability } from '../../classes/capability/capability';
import { CapabilityService } from '../../services/capability/capability.service';
import { jsPDF } from "jspdf";
import html2canvas from 'html2canvas';

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

  onSubmit() {
    let data = document.getElementById('pdf');  
        html2canvas(data).then(canvas => {
        const contentDataURL = canvas.toDataURL('image/png')  
        let pdf = new jsPDF('p','mm','a4'); //Generates PDF in landscape mode
        // let pdf = new jspdf('p', 'cm', 'a4'); Generates PDF in portrait mode
        var imgWidth = 208;
        // imgHeight nog beter aanpassen
        // de canvas wordt niet helemaal meegenomen als er capabilities bijkomen waardoor de map groter wordt
        var imgHeight = canvas.height * imgWidth / canvas.width;
        pdf.addImage(contentDataURL, 'PNG', 0, 0, imgWidth, imgHeight);  
        pdf.save('CapabilityMap.pdf');   
      }); 
  }
}
