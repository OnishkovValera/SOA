import MainComponent from "@/components/MainComponent/MainComponent.tsx";
import {ThemeProvider} from "@/components/ui/ThemeProvider.tsx";
import {useEffect, useRef, useState} from "react";
import Header from "@/pages/header/Header.tsx";
import {cn} from "@/lib/utils.ts";

function App() {
    const [showExplosion, setShowExplosion] = useState(false);
    const videoRef = useRef<HTMLVideoElement | null>(null);
    const [isSpinning, setIsSpinning] = useState(false);
    const [isPhonk, setIsPhonk] = useState(false);
    const audioRef = useRef<HTMLAudioElement | null>(null);

    useEffect(() => {
        const interval = setInterval(() => {
            setIsSpinning(true);
            setTimeout(() => setIsSpinning(false), 2000)
        }, 5000);

        const interval2 = setInterval(() => {
            setIsPhonk(true);
            console.log(("zalupa"))
            if (audioRef.current) audioRef.current.play().then(_ => {
            });
            setTimeout(() => {
                setIsPhonk(false);
                audioRef.current?.pause();
            }, 3000);
        }, 15000)

        return () => {
            clearInterval(interval);
            clearInterval(interval2);
        }
    }, [])

    const triggerExplosion = () => {
        setShowExplosion(true);
        if (videoRef.current) {
            videoRef.current.currentTime = 0;
            videoRef.current.play();
        }
    };

    const handleVideoEnd = () => {
        setShowExplosion(false);
    };

    function spinAnimateEvery5Seconds() {
        return "spin-animate"
    }

    return (
        <ThemeProvider defaultTheme="dark" storageKey="vite-ui-theme">
            <audio ref={audioRef} src="/sigma.mp3"/>
            <div
                className={cn(!isPhonk && "hidden", "fixed z-50 h-screen w-screen top-0 left-0 bg-neutral-500/50 blur-lg")}/>
            <img className={cn(!isPhonk && "hidden", "fixed z-[60] bottom-14 left-1/2 -translate-x-1/2 h-24 w-auto")}
                 src={"/skull.png"}/>
            <div className={cn((isSpinning && !isPhonk) && "animate-spin")} onClick={() => {
                audioRef.current?.play();
                audioRef.current?.pause();
            }}>
                <Header></Header>
                <MainComponent triggerExplosion={triggerExplosion}></MainComponent>
                {showExplosion && (
                    <video
                        ref={videoRef}
                        src="/explosion2.webm"
                        autoPlay
                        onEnded={handleVideoEnd}
                        className="pointer-events-none fixed inset-0 w-screen h-screen object-fill z-50"
                        style={{mixBlendMode: "normal"}}
                    />
                )}
            </div>
        </ThemeProvider>
    )
}

export default App