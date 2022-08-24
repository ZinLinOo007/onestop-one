import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { AbstractService } from './abstract.service';

export enum Status {
  Order, Paid, Shipped, Finish, Cancel
}

@Injectable({providedIn: 'root'})
export  class SaleService extends AbstractService {

  constructor(private http:HttpClient) {
    super('sale')
  }

  search(form:any) {
    return this.http.get<any[]>(this.baseApi, {params: form})
  }
}
