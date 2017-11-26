import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators, FormArray } from '@angular/forms';
import { Observable } from 'rxjs/Rx';

@Component({
  selector: 'app-data',
  templateUrl: './data.component.html',
  styles: []
})
export class DataComponent implements OnInit {

  // Objeto Forma
  forma: FormGroup;

  usuario: any = {
    nombrecompleto: {
      nombre: "Cristian",
      apellido: "peña"
    },
    email: "cristian@sss.com",
    pasatiempos: ["Correr"]
  };

  constructor() {
    // 1 la misma estructura
    this.forma = new FormGroup({
      'nombrecompleto': new FormGroup({
        'nombre': new FormControl('', [Validators.required, Validators.minLength(3)]),
        'apellido': new FormControl('', [Validators.required, this.noHerrera])
      }),
      'email': new FormControl('', [Validators.required]),
      'pasatiempos': new FormArray([
        new FormControl('Correr', Validators.required),
        new FormControl('Comer', Validators.required)
      ]),
      'pass1': new FormControl('', Validators.required),
      'pass2': new FormControl(),
      'user': new FormControl('', Validators.required, this.existeUsuario)
    })

    // Si 1 tiene la misma estructura que this.usuario, se puede hacer esto.
    //  this.forma.setValue(this.usuario);

    //Adicionar Validaciones por object
    this.forma.controls['pass2'].setValidators([Validators.required, this.passDiferents.bind(this.forma)]);
    this.forma.controls['user'].setAsyncValidators(this.existeUsuario);

    this.forma.controls['user'].valueChanges.subscribe(data => {
      console.log(data);
    });

    this.forma.controls['user'].statusChanges.subscribe(data => {
      console.log("Data => " + data);
    })

  }

  agregarPasaTiempo() {
    // (<>)   => Para decirle a typescript q es un arreglo (Castea)
    let s: FormControl = new FormControl('XXX', Validators.required);
    (<FormArray>this.forma.controls['pasatiempos']).push(s);
  }

  guardarCambios() {
    console.log(this.forma.value);
    console.log(this.forma);


    // Reestablecer al objeto ng-pristine!!
    /*
    this.forma.reset({
      nombrecompleto: {
        nombre: "",
        apellido: ""
      },
      correo: ""
    });
    */

    // Otra forma ng-pristine
    this.forma.get('email').setValue('Default@sss.com');



  }

  ngOnInit() {
  }

  /**
    Validacion Personalizada
    @param control
    @return Forma Errores en Angular     string:boolean
    string: "Nombre Validador"
    boolean: "true" Indica que dicha validaciòn no fue exitosa
  */
  noHerrera(control: FormControl): { [s: string]: boolean } {

    if (control.value == "herrera") {
      return {
        noherrera: true
      }
    }

    return null;
  }

  passDiferents(control: FormControl): { [validador: string]: boolean } {
    let forma: any = this;

    if (control.value != forma.controls['pass1'].value) {
      return {
        passdifferents: true
      }
    }
    return null;
  }

  existeUsuario(control: FormControl): Promise<any> | Observable<any> {

    let promesa = new Promise(
      (resolve, reject) => {
        setTimeout(() => {

          if (control.value == "strider") {
            resolve({ existe: true })
          } else {
            resolve(null)
          }
        }, 2000)
      }
    )
    return promesa;
  }
}
