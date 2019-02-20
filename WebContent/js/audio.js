function reproducePreview(audio, prezzo){
	var durata = 100;
	if(prezzo > 0) durata = 50; 
	
	var preview = (audio.duration/100)*durata; // 50% della durata
												
	audio.addEventListener('timeupdate', function() {
		if (!audio._startTime) audio._startTime = audio.currentTime;
			
			var playedTime = audio.currentTime - audio._startTime;
			if (playedTime >= preview){
				audio.pause();
				audio.currentTime = 0.0;
			}
		});
}