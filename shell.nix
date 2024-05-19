let
  pkgs = import <nixpkgs> {};

in
  pkgs.mkShell {
    buildInputs = with pkgs; [
      less
      vim
      jq.bin
      jdk17
    ];

    shellHook = ''
      export JPMS_HOME=${builtins.getEnv "PWD"}
      export LANG=en_US.UTF-8
    '';
  }
