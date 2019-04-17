import { Input } from 'antd';
import { Button } from 'antd';
const Search = Input.Search;

var nameTemp;

function requestAddOwner(name) {
  console.log(name);
}

export default function() {
  return(
    <div>
      <Input placeholder="name"/>
      <Button type="primary" onClick={value => console.log(value)}>OK</Button>
      <Button type="danger">Cancel</Button>
      <Search
        placeholder="input search text"
        onSearch={value => console.log(value)}
        style={{ width: 200 }}
      />
    </div>
  );
}
