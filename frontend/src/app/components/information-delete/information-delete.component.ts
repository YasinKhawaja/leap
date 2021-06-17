import { Component, Input } from '@angular/core';
import { Information } from 'src/app/classes/information/information';
import { InformationService } from 'src/app/services/information/information.service';
import Swal from 'sweetalert2';
import { InformationComponent } from '../information/information.component';

@Component({
  selector: 'app-information-delete',
  templateUrl: './information-delete.component.html',
  styleUrls: ['./information-delete.component.css']
})
export class InformationDeleteComponent {

  @Input() informationCurrentValues: Information

  constructor(private is: InformationService, private ic: InformationComponent) { }

  hide() {
    this.ic.hideAll()
  }

  deleteInformation() {
    this.is.deleteInformation(this.informationCurrentValues.id)
      .subscribe(
        () => {
          this.ic.ngOnInit()
          this.ic.hideAll()
        },
        () => {
          Swal.fire('Error', `Failed to delete Information ${this.informationCurrentValues.name}`, 'error')
        }
      )
  }

}
