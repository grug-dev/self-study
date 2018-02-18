export class FileItem {

    public archivo: File;

    public nombreArchivo: string;

    /**
     * URL de FireBase
     */
    public url: string;

    public estaSubiendo: boolean;

    public progreso: number;


    constructor(pArchivo: File) {
        this.archivo = pArchivo;
        this.nombreArchivo = this.archivo.name;
        this.estaSubiendo = false;
        this.progreso = 0;
    }

}
