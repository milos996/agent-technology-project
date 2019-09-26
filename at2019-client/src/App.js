import React, { useEffect, useState } from "react";
import { useDispatch, useSelector } from "react-redux";
import {
  fetchAgentTypes,
  fetchAgents,
  fetchPerformatives,
  createAgent,
  sendAclMessage,
  stopAgent
} from "./store/sagas/actions";
import {
  agentsSelector,
  performativesSelector,
  agentTypesSelector
} from "./store/sagas/selectors";

// const client = new W3CWebSocket('ws://localhost:8080/AT2019/socket');
const client = new WebSocket("ws://127.0.0.1:8080/AT2019/web-socket");

function App() {
  const dispatch = useDispatch();
  const agents = useSelector(agentsSelector);
  const performatives = useSelector(performativesSelector);
  const agentTypes = useSelector(agentTypesSelector);
  const [logs, setLogs] = useState([]);

  const [sendData, setSendData] = useState({
    sender: null,
    reciever: null,
    content: "",
    performative: null
  });

  const [newAgent, setNewAgent] = useState({
    name: null,
    agentType: null
  });

  const [agentToStop, setAgentToStop] = useState(null);

  function handleSendDataChange(field, value) {
    setSendData(currState => ({
      ...currState,
      [field]: value
    }));
  }

  function handleNewAgentChange(field, value) {
    setNewAgent(currState => ({
      ...currState,
      [field]: value
    }));
  }

  function handleSendMessageClick() {
    const reciever = agents.find(val => val.name === sendData.reciever);
    const sender = agents.find(val => val.name === sendData.sender);

    delete reciever.hostName;
    delete sender.hostName;

    dispatch(sendAclMessage({ ...sendData, sender, reciever }));
  }

  function handleCreateAgentClick() {
    dispatch(createAgent(newAgent));
  }

  function handleStopAgentClick() {
    const agent = agents.find(val => val.name === agentToStop);
    console.log(agent);
    dispatch(stopAgent(agent));
  }

  useEffect(() => {
    client.onopen = () => {
      client.send("Connect");
      console.log("WebSocket Client Connected");
    };
    client.onmessage = message => {
      setLogs(currState => [message, ...currState]);
    };
    client.onerror = err => {
      console.log(err);
    };
  }, []);

  useEffect(() => {
    dispatch(fetchAgentTypes());
    dispatch(fetchPerformatives());
    dispatch(fetchAgents());
  }, []);

  return (
    <div>
      <h1>AT2019</h1>
      <div className="flex-vertical">
        <div className="flex-horizontal">
          {/* send message */}
          <div className="flex-vertical block-width ml-3">
            <label>Sender</label>
            <select
              value={sendData.sender}
              onChange={e =>
                handleSendDataChange("sender", e.currentTarget.value)
              }
            >
              <option value={null}></option>
              {agents.map(agent => (
                <option value={agent.name}>{agent.name}</option>
              ))}
            </select>

            <label>Reciever</label>
            <select
              value={sendData.reciever}
              onChange={e =>
                handleSendDataChange("reciever", e.currentTarget.value)
              }
            >
              <option value={null}></option>
              {agents.map(agent => (
                <option value={agent.name}>{agent.name}</option>
              ))}
            </select>

            <label>Performatives</label>
            <select
              value={sendData.performative}
              onChange={e =>
                handleSendDataChange("performative", e.currentTarget.value)
              }
            >
              <option value={null}></option>
              {performatives.map(performative => (
                <option value={performative}>{performative}</option>
              ))}
            </select>

            <label>Content</label>
            <input
              value={sendData.content}
              onChange={e =>
                handleSendDataChange("content", e.currentTarget.value)
              }
            />

            <button onClick={handleSendMessageClick}>Send message</button>
          </div>

          {/* Create new agent */}
          <div className="flex-vertical block-width ml-3">
            <label>Agent types</label>
            <select
              value={newAgent.agentType}
              onChange={e =>
                handleNewAgentChange("agentType", e.currentTarget.value)
              }
            >
              <option value={null}></option>
              {agentTypes.map((type, index) => (
                <option key={index} value={type}>
                  {type}
                </option>
              ))}
            </select>

            <label>Name of the agent</label>
            <input
              onInput={e => handleNewAgentChange("name", e.currentTarget.value)}
            />

            <button onClick={handleCreateAgentClick}>Create agent</button>
          </div>

          {/* Stop agent */}
          <div className="flex-vertical block-width ml-3">
            <label>Agent</label>
            <select
              value={agentToStop}
              onChange={e => {
                console.log(e.currentTarget.value);
                setAgentToStop(e.currentTarget.value);
              }}
            >
              <option value={null}></option>
              {agents.map(agent => (
                <option value={agent.name}>{agent.name}</option>
              ))}
            </select>

            <button onClick={handleStopAgentClick}>Stop agent</button>
          </div>
        </div>
        <textarea
          disabled={true}
          rows={15}
          value={logs.reduce((reducer, val) => {
            return reducer + val + " \n";
          }, "")}
        ></textarea>
      </div>
    </div>
  );
}

export default App;
