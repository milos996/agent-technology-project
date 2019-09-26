import { combineReducers } from "redux";
import reducer from "./sagas/reducer/";

const rootReducer = combineReducers({
  reducer
});

export default (state, action) => {
  return rootReducer(state, action);
};
