import { Component, OnInit } from '@angular/core';
import { MaterialService } from '../../services/material.service';
import { Material } from '../../models/material.model';

@Component({
  selector: 'app-material-list',
  templateUrl: './material-list.component.html',
  styleUrls: ['./material-list.component.css']
})
export class MaterialListComponent implements OnInit {
  materiales: Material[] = [];

  constructor(private materialService: MaterialService) {}

  ngOnInit(): void {
    this.loadMaterials();
  }

  loadMaterials(): void {
    this.materialService.getAll().subscribe(data => {
      this.materiales = data;
    });
  }

  deleteMaterial(id: number): void {
    if (confirm('¿Seguro que deseas eliminar este material?')) {
      this.materialService.delete(id).subscribe(() => {
        this.loadMaterials();
      });
    }
  }
}
