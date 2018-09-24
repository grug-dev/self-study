import { contadorReducer } from './contador.reducer';
import Store from './store';
import { incrementActionType, decrementActionType, multiplyActionType } from './actions/myactions';


let oldState = {
    value : 10
}

const store = new Store( contadorReducer, oldState);
store.dispatch ( incrementActionType) ;
console.log(store.getState());

store.dispatch ( decrementActionType) ;
console.log(store.getState());

store.dispatch ( multiplyActionType) ;
console.log(store.getState());
