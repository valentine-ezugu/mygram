import { Component, OnInit } from '@angular/core';
import {UserService} from "../../services/user.service";
import {Router} from "@angular/router";
import {AuthService} from "../../services/auth.service";
import {HttpClient, HttpEventType} from "@angular/common/http";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {

  constructor(
    private userService: UserService,
    private authService: AuthService,
    private router: Router,
    private http: HttpClient
   ) { }

  ngOnInit() {
  }


  fileData: File = null;
  imageSrc: string;

  fileProgress(fileInput: any) {
    this.fileData = <File>fileInput.target.files[0];
  }

  onSubmit() {
    const formData = new FormData();
    formData.append('file', this.fileData);
    this.http.post('/api/upload', formData, {reportProgress: true, observe:'events'}).subscribe(events => {
      if (events.type == HttpEventType.UploadProgress) {
        console.log('Upload progress: ', Math.round(events.loaded / events.total * 100) + '%');
      } else if (events.type === HttpEventType.Response) {
        console.log(events);
      }
    })
  }


  hasSignedIn() {
    return !!this.userService.currentUser;
  }

  readURL(event: any): void {
    if (event.target.files && event.target.files[0]) {
      const file = event.target.files[0];

      const reader = new FileReader();
      reader.onload = (e: any)  => this.imageSrc = e.target.result;

      reader.readAsDataURL(file);
    }
  }

}
