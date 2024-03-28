% Liste des noms de fichiers audio à comparer
fichiers_audio = {'Accenteur_Mouchet.wav', 'Alouette.wav', 'Buse_A_Queue_Rousse_1.wav'};

% Parcours des fichiers audio
for i = 1:length(fichiers_audio)
    fichier = fichiers_audio{i};

    % Chargement du fichier audio
    [signal, frequence] = audioread(fichier);

    % Calcul du spectre
    spectre = abs(fft(signal));
    spectre = spectre(1:length(signal)/2+1);

    % Calcul des fréquences correspondantes
    freq = (0:length(spectre)-1) * (frequence / length(spectre));

    % Affichage du graphique des fréquences
    figure;
    subplot(2,1,1);
    plot(freq, spectre);
    title(['Spectre de ', fichier]);
    xlabel('Fréquence (Hz)');
    ylabel('Amplitude');

    % Recherche de l'amplitude maximale et de sa fréquence associée
    [amplitude_max, index_max] = max(spectre);
    frequence_max = freq(index_max);

    % Affichage de l'amplitude maximale et de la fréquence associée
    text(frequence_max, amplitude_max, ['Amplitude max: ', num2str(amplitude_max), ', Fréquence: ', num2str(frequence_max), ' Hz'], 'VerticalAlignment', 'bottom', 'HorizontalAlignment', 'left');

    % Affichage du graphique des amplitudes
    subplot(2,1,2);
    plot(signal);
    title(['Amplitude de ', fichier]);
    xlabel('Échantillon');
    ylabel('Amplitude');
end