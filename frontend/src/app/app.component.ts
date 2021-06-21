import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { DEFAULT_INTERRUPTSOURCES, Idle } from '@ng-idle/core';
import { Keepalive } from '@ng-idle/keepalive';
import Swal from 'sweetalert2';
import { JwtService } from './services/jwt/jwt.service';
import { NavbarService } from './services/navbar/navbar.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  title: string;
  environmentName: string
  username: string
  environmentId: string;
  role: string;
  timedOut = false;
  lastPing?: Date = null;

  constructor(public ns: NavbarService, public jwt: JwtService, public router: Router, private idle: Idle, private keepalive: Keepalive) {
    this.title = 'LEAP-webapp'

    // Sets the countdown to when a user is considered idle (in seconds)
    idle.setIdle(600);
    // The time the user gets before the timeout event happens (in seconds)
    idle.setTimeout(30);
    // The interrupts that reset the idle timer
    idle.setInterrupts(DEFAULT_INTERRUPTSOURCES)

    // Upon interrupting the idle timer the timer will reset and the popup will close
    idle.onIdleEnd.subscribe(() => {
      this.reset();
      Swal.close();
    });

    // When the timer ends and wasn't interrupted it will log the user out and navigate them back to the login screen, they will see a popup
    idle.onTimeout.subscribe(() => {
      this.timedOut = true;
      this.logout();
      this.router.navigate(['/login']);
      Swal.fire("warning", "Idle for over 10 minutes, log in again", "warning");
    });

    // The event that occurs when a user is idle
    idle.onIdleStart.subscribe(() => {
      Swal.fire("warning", "", "warning")
    })

    // Updates the previous swal in onIdleStart with the given message
    idle.onTimeoutWarning.subscribe((countdown) => {
      Swal.getTitle().textContent = `Idle for too long, press button within ${countdown} seconds to stay logged in`;
    })


    // Start the idle timer
    this.jwt.getUserIdle().subscribe(userIsLoggedIn => {
      if (userIsLoggedIn) {
        this.idle.watch()
        this.timedOut = false;
      } else {
        this.idle.stop();
      }
    })

    // Event checker
    keepalive.interval(15);
    keepalive.onPing.subscribe(() => this.lastPing = new Date());
  }


  reset() {
    this.idle.watch()
    this.timedOut = false;
  }

  ngOnInit(): void {
    this.jwt.setUsername()
    this.jwt.getRole()
    if (this.jwt.getUserBoolean().getValue()) {
      this.idle.watch();
      this.jwt.tokenRefresh();
    }
    if (this.ns.readCookie("jwt") != undefined && this.ns.readCookie("jwt") != null && this.ns.readCookie("jwt") != "") {
      this.jwt.getNewJwt();
    }
  }

  deselect(): void {
    this.ns.environmentDeselect()

  }

  getEnvironmentId() {
    this.environmentId = this.ns.getEnvironmentCookie();
  }

  logout() {
    this.jwt.logout();
  }

  getEnvironmentname(): string {
    this.role = this.jwt.checkRole();
    if (this.role == "application admin") {
      this.environmentName = "Companies";
    } else {
      this.environmentName = this.ns.getEnvironmentName();
    }
    return this.environmentName;
  }

  getUsername(): string {
    if (this.jwt.getUsername() != null) {
      this.username = this.jwt.getUsername();
      this.role = this.jwt.getRole()
    }
    else {
      this.username = "User";
    }
    return this.username;
  }

  getRouter(): boolean {
    if (this.router.isActive("home", true) || this.router.isActive("/", true)) {
      return true;
    } else {
      return false;
    }
  }
}
