import { Component } from "@angular/core";

@Component({
    template: `
        <div class="card">
            <div class="card-body">
                <h3>{{title}}</h3>
            </div>
        </div>
    `
})
export class AdminHomeComponent {

    title = "Admin Home"
}
