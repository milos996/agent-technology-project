import {
  FETCH_AGENTS,
  FETCH_AGENT_TYPES,
  FETCH_PERFORMATIVES,
  PUT_AGENTS,
  PUT_AGENT_TYPES,
  PUT_PERFORMATIVES,
  CREATE_AGENT,
  CREATE_ACL_MESSAGE,
  PUT_REMOVE_AGENT,
  STOP_AGENT
} from "./constants";

export const fetchAgents = () => ({
  type: FETCH_AGENTS
});

export const fetchAgentTypes = () => ({
  type: FETCH_AGENT_TYPES
});

export const fetchPerformatives = () => ({
  type: FETCH_PERFORMATIVES
});

export const putAgents = payload => ({
  type: PUT_AGENTS,
  payload
});

export const putAgentTypes = payload => ({
  type: PUT_AGENT_TYPES,
  payload
});

export const putPerformatives = payload => ({
  type: PUT_PERFORMATIVES,
  payload
});

export const createAgent = payload => ({
  type: CREATE_AGENT,
  payload
});

export const sendAclMessage = payload => ({
  type: CREATE_ACL_MESSAGE,
  payload
});

export const putRemoveAgent = payload => ({
  type: PUT_REMOVE_AGENT,
  payload
});

export const stopAgent = payload => ({
  type: STOP_AGENT,
  payload
});
