import Nav from "react-bootstrap/Nav";
import { useState } from "react";

interface MyComponentProps {
  data: {
    [key: string]: string;
  };
}
const TransDetail = ({ data }: MyComponentProps) => {
  let [tab, setTab] = useState(0);
  return (
    <div>
      <div className="wrapper">
        <div></div>
        <div>
          <div>
            <Nav variant="tabs" defaultActiveKey="/overview">
              <Nav.Item>
                <Nav.Link onClick={() => {}}>Overview</Nav.Link>
              </Nav.Item>
              <Nav.Item>
                <Nav.Link eventKey="log">Log</Nav.Link>
              </Nav.Item>
            </Nav>
          </div>
        </div>
      </div>
    </div>
  );
};

export default TransDetail;
