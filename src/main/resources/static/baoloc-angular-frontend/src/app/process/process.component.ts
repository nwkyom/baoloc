import { Component, OnInit } from '@angular/core';
import {
  Permutation,
  Result,
} from 'src/app/results-table/results-table.component';
import { ApiService } from 'src/app/api.service';
import { FormControl, Validators } from '@angular/forms';

@Component({
  selector: 'app-process',
  templateUrl: './process.component.html',
  styleUrls: ['./process.component.scss'],
})
export class ProcessComponent implements OnInit {
  customResult = new FormControl('', [
    Validators.required,
    Validators.maxLength(3),
  ]);
  result!: Result;
  sortedPermutations!: Permutation[];

  constructor(private _apiService: ApiService) {}

  ngOnInit(): void {}

  processCustomResult() {
    if (this.customResult.valid) {
      this._apiService.process(this.customResult.value).subscribe((result) => {
        this.result = result;
        this.sortedPermutations = this.result.permutations.sort();
      });
    }
  }
}
