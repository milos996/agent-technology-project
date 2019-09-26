export function puAgents(state, payload) {
  return {
    ...state,
    agents: [...state.agents, ...payload]
  };
}

export function putAgentTypes(state, payload) {
  return {
    ...state,
    agentTypes: [...state.agentTypes, ...payload]
  };
}

export function putPerformatives(state, payload) {
  return {
    ...state,
    performatives: [...state.performatives, ...payload]
  };
}

export function putRemoveAgent(state, name) {
  const index = state.agents.findIndex(val => val.name === name);

  return {
    ...state,
    agents: [
      ...state.agents.slice(0, index),
      ...state.agents.slize(index + 1, state.agents.length)
    ]
  };
}
