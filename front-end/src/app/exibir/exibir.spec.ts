import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Exibir } from './exibir';

describe('Exibir', () => {
  let component: Exibir;
  let fixture: ComponentFixture<Exibir>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [Exibir]
    })
    .compileComponents();

    fixture = TestBed.createComponent(Exibir);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
