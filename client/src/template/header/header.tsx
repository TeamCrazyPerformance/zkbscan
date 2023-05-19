import { useState } from "react";

/** Component for Header Item **/
function Item({value, isActivate} : {value:string; isActivate:boolean}){
    function handleOnClick(){
        console.log(`check header item(${value})`);
    }

    return (
        <>
            <div className={isActivate ? "active" : ""} onClick={handleOnClick}>
                {value}
            </div>
        </>
    );
}


export default function Header({ itemList }: {itemList : { name: string; isActivate: boolean}[]}) {
    const [search, setSearch] = useState("");
    const onChangeData = (e:React.FormEvent<HTMLInputElement>) => {
        setSearch(e.currentTarget.value);
    };
    return (
        <nav className="header">
            <ul className="header-search">
                <input type="text" value={search} onChange={onChangeData}/>
            </ul>
            <ul className="header-itemList-tmp">
                {itemList.map((item)=> (
                    <Item value={item.name} isActivate={item.isActivate} />
                ))}
            </ul>
        </nav>
    );
}