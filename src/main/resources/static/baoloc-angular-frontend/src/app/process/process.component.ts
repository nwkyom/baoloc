import { Component, OnInit } from '@angular/core';
import { Result } from 'src/app/results-table/results-table.component';
import { ApiService } from 'src/app/api.service';

@Component({
  selector: 'app-process',
  templateUrl: './process.component.html',
  styleUrls: ['./process.component.scss'],
})
export class ProcessComponent implements OnInit {
  customResult!: number;
  result!: Result;

  constructor(private _apiService: ApiService) {}

  ngOnInit(): void {}

  processCustomResult() {
    if (this.customResult) {
      this._apiService
        .process(this.customResult)
        .subscribe((result) => (this.result = result));
    }
  }
}
