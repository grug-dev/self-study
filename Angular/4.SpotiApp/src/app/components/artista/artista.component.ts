import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { SpotifyService } from '../../services/spotify.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-artista',
  templateUrl: './artista.component.html',
  styleUrls: ['./artista.component.css']
})
export class ArtistaComponent implements OnInit {

  artista: any[] = [];

  topTracks: any[] = [];

  constructor(private activatedRoute: ActivatedRoute, private spotifyService: SpotifyService, private router: Router) { }

  ngOnInit() {
    this.activatedRoute.params.map(
      parametros => parametros['id'])
      .subscribe(id => {
        this.spotifyService.getArtista(id).subscribe(data => this.artista = data);
        console.log(this.artista);
        console.log("Artista");

        this.spotifyService.getTopTracks(id).subscribe(data => this.topTracks = data);
      });

  }

  regresar() {
    this.router.navigate(['/buscar']);
  }

}

export interface Artista {

}
