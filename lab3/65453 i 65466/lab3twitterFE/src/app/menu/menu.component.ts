import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { CloudData, CloudOptions, ZoomOnHoverOptions } from 'angular-tag-cloud-module';

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.css']
})
export class MenuComponent implements OnInit {
  options: CloudOptions = {
    width: 1000,
    height: 400,
    overflow: false
  };

  data: CloudData[] = [];

  user: any;
  username: any;
  days: any;
  showCloud = false;

  fullname: any;
  twittername: any;
  location: any;
  usrUrl: any;
  joined: any;
  ftweet: any;
  ltweet: any;
  description: any;

  url: any;

  constructor(private http: HttpClient) {}

  ngOnInit() {
    document
      .getElementById('submitBtn')
      .addEventListener('click', this.getInput.bind(this), true);
    document
      .getElementById('submitBtn')
      .addEventListener('click', this.getInput2.bind(this), true);
    document
      .getElementById('submitBtn')
      .addEventListener('click', this.getInput3.bind(this), true);
  }

  getInput() {
    this.url = `http://localhost:5000/twitter/user-info/${this.username}`;
    this.username = (document.getElementById(
      'username'
    ) as HTMLInputElement).value;
    this.http.get(this.url).subscribe(
      response => {
        this.user = response;
        this.location = this.user.location;
        this.usrUrl = this.user.url;
        this.fullname = this.user.name;
        this.twittername = this.user.screenName;
        this.description = this.user.userDTO.description;
        this.joined = this.user.userDTO.createdAt;

        console.log(this.user);
      },
      error => {
        console.log(error);
      }
    );
  }

  getInput2() {
    this.username = (document.getElementById(
      'username'
    ) as HTMLInputElement).value;
    const url = `http://localhost:5000/twitter/first-and-last/${this.username}`;
    this.http.get(url).subscribe(
      response => {
        console.log(response);
        this.ftweet = response ? response[0].text : 'Brak';
        this.ltweet = response ? response[1].text : 'Brak';
      },
      error => {
        console.log(error);
      }
    );
  }

  getInput3() {
    this.showCloud = false;
    this.data = [];
    this.username = (document.getElementById(
      'username'
    ) as HTMLInputElement).value;
    const url = `http://localhost:5000/twitter/top-words/${this.username}`;
    this.http.get(url).subscribe(
      response => {
        console.log(response);
        Object.entries(response).forEach(([key, value]) =>
          this.data.push({
            text: value.Key,
            weight: value.Value,
          })
        );
        this.showCloud = true;
      },
      error => {
        console.log(error);
      }
    );
  }


}
