import { CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { InfoEditComponent } from './info-edit.component';

describe('InfoEditComponent', () => {
  let component: InfoEditComponent;
  let fixture: ComponentFixture<InfoEditComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ InfoEditComponent ],
      schemas: [CUSTOM_ELEMENTS_SCHEMA],
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(InfoEditComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
