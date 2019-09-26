import { take, put, call } from "redux-saga/effects";
import {
  FETCH_AGENTS,
  FETCH_AGENT_TYPES,
  FETCH_PERFORMATIVES,
  CREATE_ACL_MESSAGE,
  CREATE_AGENT,
  STOP_AGENT
} from "./constants";
import {
  putAgents,
  putAgentTypes,
  putPerformatives,
  putRemoveAgent
} from "./actions";
import apiService from "../../services/api/User";

export function* fetchAgents() {
  yield take(FETCH_AGENTS);

  const { data } = yield call(apiService.fetchAgents);
  console.log(data);
  yield put(putAgents(data));
}

export function* fetchAgentTypes() {
  yield take(FETCH_AGENT_TYPES);

  const { data } = yield call(apiService.fetchAgentTypes);

  yield put(putAgentTypes(data));
}

export function* fetchPerformatives() {
  yield take(FETCH_PERFORMATIVES);

  const { data } = yield call(apiService.fetchPerformatives);

  yield put(putPerformatives(data));
}

export function* sendAclMessage() {
  const { payload } = yield take(CREATE_ACL_MESSAGE);

  yield call(apiService.sendAclMessage, payload);
}

export function* createAgent() {
  const { payload } = yield take(CREATE_AGENT);

  const { data } = yield call(apiService.createAgent, {
    name: payload.name,
    type: payload.agentType
  });

  yield put(putAgents([data]));
}

export function* stopAgent() {
  const { payload } = yield take(STOP_AGENT);

  yield call(apiService.stopAgent, payload);

  yield put(putRemoveAgent(payload));
}
