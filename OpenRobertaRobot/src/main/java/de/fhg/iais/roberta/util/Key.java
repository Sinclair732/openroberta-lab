package de.fhg.iais.roberta.util;

public enum Key {
    SERVER_ERROR( false ),
    COMMAND_INVALID( false ),
    INIT_FAIL_MULTIPLE_FRONTENDS_ONE_HTTPSESSION( false ),
    INIT_FAIL_HTTPSESSION_EXPECTED_BUT_NOT_FOUND( false ),
    INIT_FAIL_PING_ERROR( false ),
    INIT_SUCCESS( true ),
    TOKEN_SET_SUCCESS( true ),
    TOKEN_SET_ERROR_NO_ROBOT_WAITING( false ),
    TOKEN_SET_ERROR_WRONG_ROBOTTYPE( false ),
    TOOLBOX_ERROR_ID_INVALID( false ),
    TOOLBOX_GET_ONE_ERROR_NOT_FOUND( false ),
    TOOLBOX_GET_ONE_SUCCESS( true ),
    TOOLBOX_SAVE_ERROR( false ),
    TOOLBOX_SAVE_SUCCESS( true ),
    TOOLBOX_GET_ALL_SUCCESS( true ),
    TOOLBOX_DELETE_SUCCESS( true ),
    TOOLBOX_DELETE_ERROR( false ),
    TOOLBOX_TRANSFORM_ERROR( false ),
    TOOLBOX_SAVE_ERROR_NOT_SAVED_TO_DB( false ),
    ROBOT_PUSH_RUN( true ),
    ROBOT_NOT_WAITING( false ),
    ROBOT_FIRMWAREUPDATE_POSSIBLE( true ),
    ROBOT_FIRMWAREUPDATE_IMPOSSIBLE( false ),
    ROBOT_NOT_CONNECTED( false ),
    ROBOT_SET_SUCCESS( true ),
    CONFIGURATION_ERROR_ID_INVALID( false ),
    CONFIGURATION_GET_ONE_ERROR_NOT_FOUND( false ),
    CONFIGURATION_GET_ONE_SUCCESS( true ),
    CONFIGURATION_SAVE_ERROR( false ),
    CONFIGURATION_SAVE_SUCCESS( true ),
    CONFIGURATION_GET_ALL_SUCCESS( true ),
    CONFIGURATION_DELETE_SUCCESS( true ),
    CONFIGURATION_DELETE_ERROR( false ),
    CONFIGURATION_TRANSFORM_ERROR( false ),
    CONFIGURATION_SAVE_ERROR_NOT_SAVED_TO_DB( false ),
    PROGRAM_ERROR_ID_INVALID( false ),
    PROGRAM_GET_ONE_SUCCESS( true ),
    PROGRAM_GET_ONE_ERROR_NOT_FOUND( false ),
    PROGRAM_GET_ONE_ERROR_NOT_LOGGED_IN( false ),
    PROGRAM_IMPORT_ERROR( false ),
    PROGRAM_IMPORT_ERROR_WRONG_ROBOT_TYPE( false ),
    PROGRAM_IMPORT_SUCCESS( true ),
    PROGRAM_SAVE_AS_ERROR_PROGRAM_EXISTS( false ),
    PROGRAM_SAVE_ERROR_NO_WRITE_PERMISSION( false ),
    PROGRAM_SAVE_ERROR_OPTIMISTIC_TIMESTAMP_LOCKING( false ),
    PROGRAM_SAVE_ERROR_PROGRAM_TO_UPDATE_NOT_FOUND( false ),
    PROGRAM_SAVE_SUCCESS( true ),
    PROGRAM_GET_ALL_SUCCESS( true ),
    PROGRAM_DELETE_SUCCESS( true ),
    PROGRAM_DELETE_ERROR( false ),
    PROGRAM_CONFIGURATION_NOT_COMPATIBLE( false ),
    PROGRAM_INVALID_STATEMETNS( false ),
    USER_ERROR_NOT_LOGGED_IN( false ),
    USER_LOGOUT_SUCCESS( true ),
    USER_GET_ONE_SUCCESS( true ),
    USER_EMAIL_ONE_SUCCESS( true ),
    USER_EMAIL_ONE_ERROR_USER_NOT_EXISTS_WITH_THIS_EMAIL( false ),
    USER_GET_ONE_ERROR_ID_OR_PASSWORD_WRONG( false ),
    USER_CREATE_SUCCESS( true ),
    USER_UPDATE_SUCCESS( true ),
    USER_ACTIVATION_SUCCESS( true ),
    USER_DEACTIVATION_SUCCESS( true ),
    USER_UPDATE_ERROR_ACCOUNT_WRONG( false ),
    USER_UPDATE_ERROR_NOT_SAVED_TO_DB( false ),
    USER_CREATE_ERROR_MISSING_REQ_FIELDS( false ),
    USER_CREATE_ERROR_CONTAINS_SPECIAL_CHARACTERS( false ),
    USER_CREATE_ERROR_ACCOUNT_LENGTH( false ),
    USER_CREATE_ERROR_GENERATE_CONFIRMATION( false ),
    USER_CREATE_GENERATE_CONFIRMATION_SUCCESS( true ),
    USER_CREATE_GENERATE_CONFIRMATION_URL_USERID_NOT_SAVED_IN_DATABASE( false ),
    USER_ACTIVATION_INVALID_URL( false ),
    USER_ACTIVATION_WRONG_ACCOUNT( false ),
    USER_ERROR_EMAIL_USED( false ),
    USER_CREATE_ERROR_NOT_SAVED_TO_DB( false ),
    USER_DELETE_SUCCESS( true ),
    USER_DELETE_ERROR_NOT_DELETED_IN_DB( false ),
    USER_DELETE_ERROR_ID_NOT_FOUND( false ),
    USER_GET_ALL_SUCCESS( true ),
    USER_PASSWORD_RECOVERY_GENERATE_URL_SUCCESS( true ),
    USER_PASSWORD_RECOVERY_GENERATE_URL_USERID_ERROR( false ),
    USER_PASSWORD_RECOVERY_GENERATE_URL_USERID_NOT_SAVED_IN_DATABASE( false ),
    USER_PASSWORD_RECOVERY_INVALID_URL( false ),
    USER_PASSWORD_RECOVERY_EXPIRED_URL( false ),
    USER_DANGEROUS_SYMBOLS( false ),
    OWNER_DOES_NOT_EXIST( false ),
    ROBOT_DOES_NOT_EXIST( false ),
    PROGRAM_TO_SHARE_DOES_NOT_EXIST( false ),
    USER_TO_SHARE_DOES_NOT_EXIST( false ),
    ACCOUNT_NOT_ACTIVATED_TO_SHARE( false ),
    COMPILERWORKFLOW_SUCCESS( true ),
    COMPILERWORKFLOW_ERROR_PROGRAM_NOT_FOUND( false ),
    COMPILERWORKFLOW_ERROR_CONFIGURATION_NOT_FOUND( true ), // TODO should there be another level?
    COMPILERWORKFLOW_ERROR_PROGRAM_TRANSFORM_FAILED( false ),
    COMPILERWORKFLOW_ERROR_CONFIGURATION_TRANSFORM_FAILED( false ),
    COMPILERWORKFLOW_ERROR_PROGRAM_STORE_FAILED( false ),
    COMPILERWORKFLOW_ERROR_PROGRAM_COMPILE_FAILED( false ),
    COMPILERWORKFLOW_ERROR_PROGRAM_GENERATION_FAILED( false ),
    COMPILERWORKFLOW_ERROR_PROGRAM_GENERATION_FAILED_WITH_PARAMETERS( false ),
    COMPILERWORKFLOW_PROGRAM_GENERATION_SUCCESS( true ),
    COMPILERWORKFLOW_PROJECT_BUILD_SUCCESS( true ),
    ACCESS_RIGHT_CHANGED( true ),
    ACCESS_RIGHT_DELETED( true ),
    USER_PASSWORD_RECOVERY_SENT_MAIL_FAIL( false ),
    USER_PASSWORD_RECOVERY_SENT_MAIL_SUCCESS( true ),
    USER_ACTIVATION_SENT_MAIL_FAIL( false ),
    USER_ACTIVATION_SENT_MAIL_SUCCESS( true ),
    GALLERY_UPLOAD_SUCCESS( true ),
    GALLERY_UPLOAD_ERROR( false ),
    LIKE_SAVE_SUCCESS( true ),
    LIKE_SAVE_ERROR_EXISTS( false ),
    LIKE_GET_ONE_ERROR_NOT_FOUND( false ),
    LIKE_GET_ONE_SUCCESS( true ),
    LIKE_GET_ALL_SUCCESS( true ),
    LIKE_DELETE_SUCCESS( true ),
    RASPBERRY_PROGRAM_UPLOAD_ERROR( false ),
    VORWERK_PROGRAM_UPLOAD_ERROR( false ),
    LIST_CREATE_WITH_ERROR( false ), 
    FIRMWARE_RESET_SUCCESS(true),
    FIRMWARE_RESET_ERROR(false);

    /**
     * Creates a new Key and adds the needed ORA_ prefix for the blockly message.
     * 
     * @param success whether this message signifies a success
     */
    Key(boolean success) {
        this.key = "ORA_" + this;
        this.success = success;
    }

    private final String key;
    private final boolean success;

    /**
     * get the key string. This string is looked up in a message table supplied by blockly. Blockly is running in the frontend. Thus, the typical use is:<br>
     * - return from somewhere in the server an enum object<br>
     * - the REST services (or their helper methods) take the key string and embed it into a JSON object returned to the (javascript) client<br>
     * - the client uses a language specific map to get the real message
     */
    public String getKey() {
        return this.key;
    }

    public boolean isSuccess() {
        return this.success;
    }
}
