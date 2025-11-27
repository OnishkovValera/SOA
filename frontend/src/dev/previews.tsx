import {ComponentPreview, Previews} from "@react-buddy/ide-toolbox";
import {ExampleLoaderComponent, PaletteTree} from "./palette";
import App from "@/App.tsx";
import Header from "@/pages/header/Header.tsx";

const ComponentPreviews = () => {
    return (
        <Previews palette={<PaletteTree/>}>
            <ComponentPreview path="/App">
                <App/>
            </ComponentPreview>
            <ComponentPreview
                path="/ExampleLoaderComponent">
                <ExampleLoaderComponent/>
            </ComponentPreview>
            <ComponentPreview path="/Header">
                <Header/>
            </ComponentPreview>
        </Previews>
    );
};

export default ComponentPreviews;