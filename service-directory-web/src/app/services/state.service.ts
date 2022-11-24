import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environments } from 'src/environments/environment';

const API = `${environments.baseApi}/state`

@Injectable({
  providedIn: 'root'
})
export class StateService {

  constructor(private http:HttpClient) { }

  search(form:any) {
    return this.http.get<any[]>(API, {params: form})
  }

  findById(id:any) {
    return this.http.get<any>(`${API}/${id}`)
  }

  save(form:any) {
    const {id, ... param} = form
    return id == 0 ? this.create(param) : this.update(id, param)
  }

  private create(param:any) {
    return this.http.post(API, param)
  }

  private update(id:any, param:any) {
    return this.http.put(`${API}/${id}`, param)
  }
}
