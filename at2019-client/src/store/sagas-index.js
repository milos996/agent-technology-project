import { all, spawn, call, put } from "redux-saga/effects";
import flatten from "lodash.flatten";
import * as appSagas from "./sagas/saga";

export default function* rootSaga() {
  let sagas = flatten(
    [appSagas].map(saga =>
      Object.keys(saga).map(sagaFunctionName => saga[sagaFunctionName])
    )
  );

  yield all(
    sagas.map(saga =>
      spawn(function*() {
        while (true) {
          try {
            yield call(saga);
          } catch (e) {}
        }
      })
    )
  );
}
