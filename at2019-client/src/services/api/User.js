import HttpBaseClient from "../HttpBaseClient";
import { format } from "util";

const ENDPOINTS = {
  AGENTS: "/agents/running",
  AGENT_TYPES: "/agents/classes",
  PERFORMATIVES: "/messages",
  SEND_ACL_MESSAGE: "/messages",
  CREATE_AGENT: "/agents/running/%s/%s",
  DELETE_AGENT: "/agents/running/%s"
};

class ApiService extends HttpBaseClient {
  fetchAgentTypes = () => {
    return this.getApiClient().get(ENDPOINTS.AGENT_TYPES);
  };

  fetchPerformatives = () => {
    return this.getApiClient().get(ENDPOINTS.PERFORMATIVES);
  };

  fetchAgents = () => {
    return this.getApiClient().get(ENDPOINTS.AGENTS);
  };

  sendAclMessage = data => {
    return this.getApiClient().post(ENDPOINTS.SEND_ACL_MESSAGE, data);
  };

  createAgent = ({ type, name }) => {
    console.log(format(ENDPOINTS.CREATE_AGENT, type, name));
    return this.getApiClient().put(format(ENDPOINTS.CREATE_AGENT, type, name));
  };

  stopAgent = data => {
    const { hostName, ...rest } = data;
    return this.getApiClient().delete(ENDPOINTS.AGENTS, {
      data: rest
    });
  };
}
export default new ApiService();
