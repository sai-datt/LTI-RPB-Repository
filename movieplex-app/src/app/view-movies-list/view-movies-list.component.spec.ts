import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewMoviesListComponent } from './view-movies-list.component';

describe('ViewMoviesListComponent', () => {
  let component: ViewMoviesListComponent;
  let fixture: ComponentFixture<ViewMoviesListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ViewMoviesListComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ViewMoviesListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
