import { Component, OnInit } from '@angular/core';
import { SpotifyService } from '../../services/spotify.service';

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styles: []
})
export class SearchComponent implements OnInit {

  termino: string = "";

  constructor(private spotifyService: SpotifyService) { }

  ngOnInit() {

  }


  buscarArtista() {
    // Para escuchar respuesta observable
    this.spotifyService.getArtistas(this.termino).subscribe();
  }

}
