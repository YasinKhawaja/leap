import { Component, OnInit, ViewChild } from '@angular/core';
import { Capability } from 'src/app/classes/capability/capability';
import { CSVRecord } from 'src/app/classes/csvrecord/csvrecord';
import { JwtService } from 'src/app/services/jwt/jwt.service';
import { NavbarService } from 'src/app/services/navbar/navbar.service';
import Swal from 'sweetalert2';
import { CapabilityService } from '../../services/capability/capability.service';

@Component({
  selector: 'app-capability',
  templateUrl: './capability.component.html',
  styleUrls: ['./capability.component.css']
})

export class CapabilityComponent implements OnInit {

  capabilities: Capability[] = [];
  // For setCapability()
  _cap: Capability;
  // For display()
  _display1: boolean = false;
  _display2: boolean = false;
  _display3: boolean = false;

  fileExtensions: string[] = []
  records: any[] = []
  @ViewChild('csvReader') csvReader: any;

  constructor(private cs: CapabilityService, private ns: NavbarService, public jwt: JwtService) {
    this._cap = null;
    this.fileExtensions.push("csv")
    this.fileExtensions.push("xlsx")
    this.fileExtensions.push("xls")
  }

  ngOnInit(): void {
    this.ns.environmentSelect();

    var envId = this.ns.getEnvironmentCookie();

    this.cs.getAllCapabilitiesInEnvironment(envId)
      .subscribe(res => { this.capabilities = res; }, err => console.error(err));
  }

  uploadFile($event: any): void {
    var fileExtension = $event.target.files[0].name.split(".").pop().toLowerCase();

    if (this.fileExtensions.includes(fileExtension)) {
      alert('Please wait a few minutes so the capabilities can load in.')
      let input = $event.target
      let reader = new FileReader()

      reader.readAsText(input.files[0])

      reader.onload = async () => {
        let csvData = reader.result
        let csvRecords = (<string>csvData).split(/\r\n|\n/)

        let headers = (<string>csvRecords[0]).split(';')
        let headersRow = []
        for (let j = 0; j < headers.length; j++) {
          headersRow.push(headers[j]);
        }

        await this.getDataFromCSV(csvRecords, headersRow.length)
      }

      reader.onerror = () => {
        Swal.fire('Error', 'Error reading the file, please contact support', 'error')
      }

    } else {
      Swal.fire('Error', `Unsupported file extension (.${fileExtension}) detected. Please upload a .csv, .xls or .xlsx file.`, 'error')
      this.fileReset();
    }
  }

  fileReset() {
    this.csvReader.nativeElement.value = ""
    this.records = []
  }

  async getDataFromCSV(csvRecords: any, headerLength: any) {

    var environmentid = this.ns.getEnvironmentCookie();

    for (let i = 1; i < csvRecords.length; i++) {
      let currentRecord = (<string>csvRecords[i]).split(';');
      if (currentRecord.length == headerLength) {
        let csvRecord: CSVRecord = new CSVRecord();

        csvRecord.name = currentRecord[2].trim().slice(1, currentRecord[2].trim().length - 1)
        csvRecord.paceOfChange = currentRecord[3].trim().slice(1, currentRecord[3].trim().length - 1)
        csvRecord.targetOperationModel = currentRecord[4].trim().slice(1, currentRecord[4].trim().length - 1)
        if (currentRecord[5].trim() != "") {
          csvRecord.resourceQuality = currentRecord[5].trim()
        }
        if (currentRecord[9].trim() != "") {
          csvRecord.parent = currentRecord[9].trim().slice(1, currentRecord[9].trim().length - 1)
        }
        console.log(csvRecord)

        var capability = new Capability(csvRecord.name, csvRecord.paceOfChange, csvRecord.targetOperationModel, csvRecord.resourceQuality)
        await this.cs.createCapabilityFromCsv(environmentid, capability, csvRecord.parent).toPromise()
      }
    }
    this.cs.getAllCapabilitiesInEnvironment(environmentid)
      .subscribe(
        res => {
          this.capabilities = res
        },
        () => {
          Swal.fire('Error', `Failed to load capabilities`, 'error')
        })
  }

  selectCapability(capabilityID: string): void {
    this.ns.setCapabilityCookie(capabilityID);
  }

  // To set the _cap prop to give its value to child comps
  private setCapability(cap: Capability) {
    this._cap = cap;
  }

  // To display the "capability-add" comp
  private display(column: string) {
    switch (column) {
      case '1':
        this._display1 = !this._display1;
        this._display2 = false;
        this._display3 = false;
        break;
      case '2':
        this._display1 = false;
        this._display2 = !this._display2;
        this._display3 = false;
        break;
      case '3':
        this._display1 = false;
        this._display2 = false;
        this._display3 = !this._display3;
        break;
      default:
        break;
    }
  }

  // To call the 2 methods above
  callAll(cap: Capability, column: string) {
    this.setCapability(cap);
    this.display(column);
  }

}