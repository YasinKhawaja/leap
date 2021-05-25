import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ReactiveFormsModule } from '@angular/forms';
import { RouterTestingModule } from '@angular/router/testing';
import { EnvironmentAddComponent } from './environment-add.component';


describe('EnvironmentAddComponent', () => {
  let component: EnvironmentAddComponent;
  let fixture: ComponentFixture<EnvironmentAddComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [
        RouterTestingModule,
        HttpClientTestingModule,
        ReactiveFormsModule
      ],
      declarations: [
        EnvironmentAddComponent
      ],
      providers: [
        // temp
      ]
    })
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(EnvironmentAddComponent);
    component = fixture.componentInstance;
    component.ngOnInit(); // Initialize envAddForm before each test = When
    fixture.detectChanges();
  });

  // Default test
  it('should create component', () => {
    expect(component).toBeTruthy();
  });

  // Test "envAddForm" validality
  it('should test form validality', () => {
    // Given
    var form = component.envAddForm;
    var name = form.get('name');

    // Then
    // valid input = valid form
    name.setValue('Test');
    expect(form.valid).toBeTruthy();

    // invalid input = invalid form
    name.setValue('');
    expect(form.valid).toBeFalsy();
  });

  // Test "envAddForm" input validality (name)
  it('should test input validality', () => {
    // Given
    var name = component.envAddForm.get('name');

    // Then
    // valid name = valid input
    name.setValue('Test');
    expect(name.valid).toBeTruthy();

    // no name = required error & invalid input
    name.setValue('');
    expect(name.errors.required).toBeTruthy();
    expect(name.valid).toBeFalsy();

    // invalid name = pattern error & invalid input
    name.setValue(' Test@7 ');
    expect(name.errors.pattern).toBeTruthy();
    expect(name.valid).toBeFalsy();
  });

});
