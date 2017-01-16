;;; java-repl --- Major mode for java-repl

;; Copyright 2014 Colin Carr
;;
;; Author: colinpcarr@member.fsf.org
;; Time-stamp: <2014-02-14 22:48:10 cpc26>

;; This program is free software; you can redistribute it and/or modify
;; it under the terms of the GNU General Public License as published by
;; the Free Software Foundation; either version 2, or (at your option)
;; any later version.
;;
;; This program is distributed in the hope that it will be useful,
;; but WITHOUT ANY WARRANTY; without even the implied warranty of
;; MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
;; GNU General Public License for more details.
;;
;; You should have received a copy of the GNU General Public License
;; along with this program; if not, write to the Free Software
;; Foundation, Inc., 675 Mass Ave, Cambridge, MA 02139, USA.

;;; Commentary:

;;  This is  E N T E R P R I S E  Q U A L I T Y  repl work.

;; Put this file into your load-path and the following into your ~/.emacs:
;;   (require 'jav-repl)

;
; CUSTOM  -
(defgroup java-repl nil
  "Major mode for java-repl"
  :group 'languages)

(defcustom java-repl-command "java-repl"
  "The 'java-repl' command.  Invoke the 'java-repl' via a wrapper that execs.
#!/usr/bin/env bash
exec /usr/bin/java -jar $JAVAREPLPATH/bin/java-repl.jarlocalhost:java-repl
JAVAREPLPATH is installed directory.
Customize this variable to point to the wrapper script."
  :type 'string
  :group 'java-repl)

;;; Code:
(defvar java-repl-file-path "/Users/cpc26/opt/java_repl/bin/java-repl"
  "Path to the program used by `run-java-repl'.")
;
(defvar java-repl-cli-arguments '()
  "Commandline arguments to pass to `java-repl-cli'.")
;
(defvar java-repl-mode-map
  (let ((map (nconc (make-sparse-keymap) comint-mode-map)))
    ;; example definition
    (define-key map "\t" 'completion-at-point)
    map)
  "Basic mode map for `run-java-repl'.")
;
(defvar java-repl-prompt-regexp "^\\(?:\\[[^@]+@[^@]+\\]\\)"
  "Prompt for `run-java-repl'.")
;
(defun run-java-repl ()
  "Run an inferior instance of `java-repl' inside Emacs."
  (interactive)
  (let* ((java-repl-program java-repl-file-path)
         (buffer (comint-check-proc "java-repl")))
    ;; pop to the "*java-repl*" buffer if the process is dead, the
    ;; buffer is missing or it's got the wrong mode.
    (pop-to-buffer-same-window
     (if (or buffer (not (derived-mode-p 'java-repl-mode))
             (comint-check-proc (current-buffer)))
         (get-buffer-create (or buffer "*java-repl*"))
       (current-buffer)))
    ;; create the comint process if there is no buffer.
    (unless buffer
      (apply 'make-comint-in-buffer "java-repl" buffer
             java-repl-program java-repl-cli-arguments)
      (java-repl-mode))))
(defun java-repl--initialize ()
  "Helper function to initialize java-repl."
  (setq comint-process-echoes t)
  (setq comint-use-prompt-regexp t))
;
(define-derived-mode java-repl-mode comint-mode "java-repl"
  "Major mode for `run-java-repl'.

\\<java-repl-mode-map>"
  nil "java-repl"
  ;; this sets up the prompt so it matches things like: [foo@bar]
  (setq comint-prompt-regexp java-repl-prompt-regexp)
  ;; this makes it read only; a contentious subject as some prefer the
  ;; buffer to be overwritable.
  (setq comint-prompt-read-only t)
  ;; this makes it so commands like M-{ and M-} work.
  (set (make-local-variable 'paragraph-separate) "\\'")
  (set (make-local-variable 'font-lock-defaults) '(java-repl-font-lock-keywords t))
  (set (make-local-variable 'paragraph-start) java-repl-prompt-regexp))

;; this has to be done in a hook. grumble grumble.
(add-hook 'java-repl-mode-hook 'java-repl--initialize)
;;;; END JAVA-REPL

(provide 'java-repl)

;;; java-repl.el ends here
