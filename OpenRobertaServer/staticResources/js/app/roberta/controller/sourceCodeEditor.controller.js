define(["require", "exports", "message", "util.roberta", "guiState.controller", "program.model", "program.controller", "progRun.controller", "import.controller", "blockly", "codeflask", "jquery"], function (require, exports, MSG, UTIL, GUISTATE_C, PROGRAM, PROG_C, PROGRUN_C, IMPORT_C, Blockly, CodeFlask, $) {
    Object.defineProperty(exports, "__esModule", { value: true });
    exports.clickSourceCodeEditor = exports.resetScroll = exports.setCodeLanguage = exports.init = void 0;
    var flask;
    var currentLanguage;
    var wasEditedByUser;
    function init() {
        flask = new CodeFlask('#flaskEditor', {
            language: 'java',
            lineNumbers: true,
            tabSize: 4,
        });
        initEvents();
    }
    exports.init = init;
    function clickSourceCodeEditor() {
        getSourceCode(true);
    }
    exports.clickSourceCodeEditor = clickSourceCodeEditor;
    function setCodeLanguage(language) {
        var langToSet;
        switch (language) {
            case 'py':
                langToSet = 'python';
                break;
            case 'java':
                langToSet = 'java';
                break;
            case 'ino':
            case 'nxc':
            case 'cpp':
                langToSet = 'clike';
                break;
            case 'json':
                langToSet = 'js';
                break;
            default:
                langToSet = 'js';
        }
        flask.updateLanguage(langToSet);
        currentLanguage = langToSet;
    }
    exports.setCodeLanguage = setCodeLanguage;
    function resetScroll() {
        $('.codeflask__pre').attr('transform', 'translate3d(0px, 0px, 0px)');
        $('.codeflask__lines').attr('transform', 'translate3d(0px, 0px, 0px)');
    }
    exports.resetScroll = resetScroll;
    function initEvents() {
        flask.onUpdate(function (code) {
            if ($('#sourceCodeEditorPane').hasClass('active')) {
                wasEditedByUser = true;
            }
        });
        $('#backSourceCodeEditor').onWrap('click', function () {
            if (wasEditedByUser) {
                $('#show-message-confirm').oneWrap('shown.bs.modal', function (e) {
                    $('#confirm').off();
                    $('#confirm').on('click', function (e) {
                        e.preventDefault();
                        wasEditedByUser = false;
                        $('#tabProgram').clickWrap();
                    });
                    $('#confirmCancel').off();
                    $('#confirmCancel').on('click', function (e) {
                        e.preventDefault();
                        $('.modal').modal('hide');
                    });
                });
                MSG.displayMessage('SOURCE_CODE_EDITOR_CLOSE_CONFIRMATION', 'POPUP', '', true, false);
            }
            else {
                wasEditedByUser = false;
                $('#tabProgram').clickWrap();
            }
            return false;
        }, 'back to previous view');
        $('#runSourceCodeEditor').onWrap('click', function () {
            PROGRUN_C.runNative(flask.getCode());
            return false;
        }, 'run button clicked');
        $('#buildSourceCodeEditor').onWrap('click', function () {
            GUISTATE_C.setRunEnabled(false);
            $('#buildSourceCodeEditor').addClass('disabled');
            PROGRAM.compileN(GUISTATE_C.getProgramName(), flask.getCode(), GUISTATE_C.getLanguage(), function (result) {
                if (result.rc == 'ok') {
                    MSG.displayMessage(result.message, 'POPUP', '', false, false);
                }
                else {
                    MSG.displayInformation(result, result.message, result.message, GUISTATE_C.getProgramName());
                }
                GUISTATE_C.setRunEnabled(true);
                $('#buildSourceCodeEditor').removeClass('disabled');
            });
            return false;
        }, 'build button clicked');
        $('#downloadSourceCodeEditor').onWrap('click', function () {
            var filename = GUISTATE_C.getProgramName() + '.' + GUISTATE_C.getSourceCodeFileExtension();
            UTIL.download(filename, flask.getCode());
            MSG.displayMessage('MENU_MESSAGE_DOWNLOAD', 'TOAST', filename);
            return false;
        }, 'download source code button clicked');
        $('#uploadSourceCodeEditor').onWrap('click', function () {
            IMPORT_C.importSourceCode(function (name, source) {
                flask.updateCode(source);
            });
            return false;
        }, 'upload source code button clicked');
        $('#importSourceCodeEditor').onWrap('click', function () {
            getSourceCode(false);
            return false;
        }, 'import from blockly button clicked');
        $('#tabSourceCodeEditor').onWrap('show.bs.tab', function () {
            if (currentLanguage === 'python' || currentLanguage === 'json') {
                $('#buildSourceCodeEditor').addClass('disabled');
            }
            $('#main-section').css('background-color', '#EEE');
        }, 'in show source code editor');
        $('#tabSourceCodeEditor').onWrap('shown.bs.tab', function () {
            GUISTATE_C.setView('tabSourceCodeEditor');
        }, 'after show source code editor');
        $('#tabSourceCodeEditor').on('hide.bs.tab', function () {
            $('#buildSourceCodeEditor').removeClass('disabled');
            $('#main-section').css('background-color', '#FFF');
        });
        $('#sourceCodeEditorPane')
            .find('button[name="rightMostButton"]')
            .attr('title', '')
            .attr('rel', 'tooltip')
            .attr('data-placement', 'left')
            .attr('lkey', 'Blockly.Msg.SOURCE_CODE_EDITOR_IMPORT_TOOLTIP')
            .attr('data-original-title', Blockly.Msg.SOURCE_CODE_EDITOR_IMPORT_TOOLTIP)
            .tooltip('fixTitle');
    }
    function getSourceCode(reload) {
        var blocklyWorkspace = GUISTATE_C.getBlocklyWorkspace();
        var dom = Blockly.Xml.workspaceToDom(blocklyWorkspace);
        var xmlProgram = Blockly.Xml.domToText(dom);
        var isNamedConfig = !GUISTATE_C.isConfigurationStandard() && !GUISTATE_C.isConfigurationAnonymous();
        var configName = isNamedConfig ? GUISTATE_C.getConfigurationName() : undefined;
        var xmlConfigText = GUISTATE_C.isConfigurationAnonymous() ? GUISTATE_C.getConfigurationXML() : undefined;
        var language = GUISTATE_C.getLanguage();
        PROGRAM.showSourceProgram(GUISTATE_C.getProgramName(), configName, xmlProgram, xmlConfigText, PROG_C.SSID, PROG_C.password, language, function (result) {
            PROG_C.reloadProgram(result);
            if (result.rc == 'ok') {
                if (reload) {
                    $('#tabSourceCodeEditor').clickWrap();
                }
                GUISTATE_C.setState(result);
                flask.updateCode(result.sourceCode);
            }
            else {
                MSG.displayInformation(result, result.message, result.message, result.parameters);
            }
        });
    }
});
