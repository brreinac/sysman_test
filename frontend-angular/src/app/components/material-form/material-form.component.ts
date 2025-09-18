import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { MaterialService } from '../../services/material.service';
import { Material } from '../../models/material.model';

@Component({
  selector: 'app-material-form',
  templateUrl: './material-form.component.html',
  styleUrls: ['./material-form.component.css']
})
export class MaterialFormComponent implements OnInit {
  material: Material = {
    nombre: '',
    descripcion: '',
    tipo: '',
    precio: 0,
    fechaCompra: '',
    fechaRegistro: ''
  };
  isEdit = false;

  constructor(
    private materialService: MaterialService,
    private route: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit(): void {
    const id = this.route.snapshot.paramMap.get('id');
    if (id) {
      this.isEdit = true;
      this.materialService.getById(+id).subscribe(data => {
        this.material = data;
      });
    }
  }

  onSubmit(): void {
    if (this.isEdit) {
      this.materialService.update(this.material.id!, this.material).subscribe(() => {
        this.router.navigate(['/materiales']);
      });
    } else {
      this.materialService.create(this.material).subscribe(() => {
        this.router.navigate(['/materiales']);
      });
    }
  }
}
