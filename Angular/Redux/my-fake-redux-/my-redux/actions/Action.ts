import { State } from '../State';

export interface Action {

    send(  oldState: State ) :  State;

}