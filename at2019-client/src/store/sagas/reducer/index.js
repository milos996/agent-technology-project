import {
  PUT_PERFORMATIVES,
  PUT_AGENT_TYPES,
  PUT_AGENTS,
  PUT_REMOVE_AGENT
} from "../constants";
import * as computationFunctions from "./computation-functions";

const initialState = {
  agentTypes: [],
  performatives: [],
  agents: []
};

const reducer = (state = initialState, { type, payload }) => {
  if (actionHandler.hasOwnProperty(type)) {
    return actionHandler[type](state, payload);
  }

  return state;
};

const actionHandler = {
  [PUT_AGENTS]: computationFunctions.puAgents,
  [PUT_AGENT_TYPES]: computationFunctions.putAgentTypes,
  [PUT_PERFORMATIVES]: computationFunctions.putPerformatives,
  [PUT_REMOVE_AGENT]: computationFunctions.putRemoveAgent
};

export default reducer;
