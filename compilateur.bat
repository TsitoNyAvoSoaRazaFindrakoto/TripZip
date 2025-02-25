@echo off
setlocal EnableDelayedExpansion


rem Definir le chemin d'acces au repertoire des sources et au repertoire de destination des fichiers compiles
set "sourceDirectory=src"
set "destinationDirectory=bin"

REM Chemin vers le repertoire contenant les bibliotheques necessaires
set "libDirectory=%WORK_DIR%\lib"

rem Initialiser la liste des fichiers Java a compiler
set "javaFiles="

rem Recuperer la liste de tous les fichiers Java dans les sous-dossiers de %sourceDirectory%
for /r "%sourceDirectory%" %%G in (*.java) do (
    rem Extraire la structure des packages a partir du chemin complet du fichier source
    set "javaFile=%%~fG"
    set "packagePath=!javaFile:%sourceDirectory%=!"
    set "packagePath=!packagePath:~0,-\%%~nG%%~xG!"

    rem Creer les repertoires de sortie si necessaire
    if not exist "%destinationDirectory%!packagePath!" (
        mkdir "%destinationDirectory%!packagePath!" >nul
    )

    rem Ajouter le fichier Java a la liste des fichiers a compiler
    set "javaFiles=!javaFiles! %%G"
)

rem Construire le chemin de classe pour toutes les bibliotheques dans le dossier "lib"
set "classpath="
for %%I in ("%libDirectory%\*.jar") do (
    set "classpath=!classpath!;%%I"
)

rem Compiler tous les fichiers Java en une seule commande avec les bibliotheques necessaires
javac -parameters -cp "%classpath%" -d "%destinationDirectory%" !javaFiles!

endlocal