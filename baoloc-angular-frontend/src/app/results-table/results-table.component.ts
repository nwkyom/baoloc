import { AfterViewInit, Component, ViewChild, OnInit } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatSnackBar } from '@angular/material/snack-bar';
import { MatSort } from '@angular/material/sort';
import { MatTable, MatTableDataSource } from '@angular/material/table';
import { ApiService } from '../api.service';

export interface Result {
  id: number;
  expectedResult: number;
  duration: number;
  permutations: Permutation[];
}

export interface Permutation {
  id: number;
  numbers: string;
}

@Component({
  selector: 'app-results-table',
  templateUrl: './results-table.component.html',
  styleUrls: ['./results-table.component.scss'],
})
export class ResultsTableComponent implements OnInit, AfterViewInit {
  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;
  @ViewChild(MatTable) table!: MatTable<Result>;

  dataSource = new MatTableDataSource<Result>();

  /** Columns displayed in the table. Columns IDs can be added, removed, or reordered. */
  displayedColumns = [
    'id',
    'expectedResult',
    'duration',
    'nbPermutations',
    'permutations',
    'actions',
  ];

  constructor(
    private _apiService: ApiService,
    private _snackBar: MatSnackBar
  ) {}

  ngOnInit(): void {
    this.loadResults();
  }

  loadResults = () => {
    this._apiService.getResults().subscribe((results) => {
      this.dataSource.data = results as Result[];
    });
  };

  deleteResult(result: Result) {
    this._apiService.deleteResult(result).subscribe((resp) => {
      this.loadResults();
      this._snackBar.open(resp, 'Dismiss');
    });
  }

  ngAfterViewInit(): void {
    this.dataSource.sort = this.sort;
    this.dataSource.paginator = this.paginator;
    this.table.dataSource = this.dataSource;
  }

  formatPermutationsNumbers(permutations: Permutation[]) {
    return permutations
      .map((permutation) => permutation.numbers)
      .map((numbers) => `(${numbers})`);
  }
}
