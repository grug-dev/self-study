
import { Store, createStore } from 'redux';
import { contadorReducer } from './contador.reducer';
import { incrementActionType } from './actions/myactions';

const store: Store = createStore( contadorReducer );

store.subscribe( () => {

    console.log('Subs:', store.getState() );

});


console.log("My Type: " + incrementActionType);

store.dispatch( incrementActionType );
store.dispatch( incrementActionType );
store.dispatch( incrementActionType );
store.dispatch( incrementActionType );
store.dispatch( incrementActionType );
store.dispatch( incrementActionType );