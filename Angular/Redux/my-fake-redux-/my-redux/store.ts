import { State } from './State';
import { Reducer } from './myreducer';
import { contadorReducer } from './contador.reducer';
import { ActionType } from './ActionType';



class Store<T> {

    constructor ( private reducer : Reducer<T>, private state : T ) {    }


    getState() {       
        return this.state;
    }

    dispatch ( action : ActionType) {

        this.state = this.reducer( this.state , action);


    }

}

export default Store;