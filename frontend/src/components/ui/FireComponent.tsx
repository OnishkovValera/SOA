import {useState, useRef} from "react";

export default function ExplosionPage() {
    const [showExplosion, setShowExplosion] = useState(false);
    const videoRef = useRef<HTMLVideoElement | null>(null);

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

    return (
        <div className="relative bg-gray-900 text-white">
            <div className="p-10 space-y-6">
                <h1 className="text-4xl font-bold">Моя страница</h1>
                <p className="text-lg">Нажми кнопку — и произойдёт взрыв 😎</p>

                <button
                    onClick={triggerExplosion}
                    className="px-6 py-3 bg-red-500 rounded-lg text-lg hover:bg-red-600"
                >
                    Бахнуть взрыв 💥
                </button>
            </div>

            {/* Слой взрыва */}
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
    );
}
