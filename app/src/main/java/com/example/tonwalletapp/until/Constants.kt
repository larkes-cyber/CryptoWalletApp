package com.example.tonwalletapp.until

import android.graphics.YuvImage

object Constants {

    const val TonWalletTitle = "TON Wallet"
    const val TonWalletText = "TON Wallet allows you to make fast and\n" +
            "secure blockchain-based payments\n" +
            "without intermediaries."
    const val CreateWalletBtnText = "Create my wallet"
    const val ImportWalletBtnText = "Import existing wallet"
    const val TonCongratsTitle = "Congratulations"
    const val TonCongratsText = "Your TON Wallet has just been created. \n" +
            "Only you control it.\n" +
            "\n" +
            "To be able to always have access to it, please write down secret words and set up a secure passcode."
    const val ProceedBtnText = "Proceed"
    const val TonRecoveryPhraseTitle = "Your Recovery Phrase"
    const val TonRecoveryPhraseText = "Write down these 24 words in this exact order and keep them in a secure place. Do not share this list with anyone. If you lose it, you will irrevocably lose access to your TON account."
    const val DoneBtnText = "Done"
    const val DidntHaveEnoughTimeTitle = "Sure done?"
    const val DidntHaveEnoughTimeText = "You didn’t have enough time to\n" +
            "write these words down."
    const val DidntHaveEnoughTimeAgreeOption = "OK, sorry"
    const val DidntHaveEnoughTimeSkipOption = "Skip"
    const val TestTimeTitle = "Test Time!"
    const val TestTimeText = "Let’s check that you wrote them down correctly. Please enter the words \n"
    const val ContinueBtnText = "Continue"
    const val IncorrectWordErrorTitle = "Incorrect words"
    const val IncorrectWordErrorText = "The secret words you have entered\n" +
            "do not match the ones in the list."
    const val IncorrectWordErrorSeeWordsOption = "See words"
    const val IncorrectWordErrorTryAgainOption = "Try again"
    const val PerfectTitle = "Perfect!"
    const val PerfectText = "Now set up a passcode to secure\n" +
            "transactions."
    const val PerfectBtnText = "Set a Passcode"
    const val SetPasswordTitle = "Set a Passcode"
    const val SetPasswordText = "Enter the 4 digits in the passcode."
    const val ConfirmPasswordTitle = "Confirm a Passcode"
    const val ReadyToGoTitle = "Ready to go!"
    const val ReadyToGoText = "You are all set. Now you have a wallet that\n" +
            "only you control — directly, without\n" +
            "middlemen or bankers. "
    const val VisitWalletBtnText = "View my wallet"
    const val ImportPhraseTitle = "24 Secret Words"
    const val ImportPhraseText = "You can restore access to your wallet by entering 24 words you wrote when down you creating the wallet."
    const val DontHavePhraseBtnTitle = "I don’t have those"
    const val ContinueBtnTitle = "Continue"
    const val IncorrectImportedPhraseText = "Sorry, you have entered incorrect secret words. Please double check and try again."
    const val OkBtnText = "OK"
    const val ImportSuccessTitle = "Your wallet has just been imported!"
    const val WrongPhraseTitle = "Too Bad!"
    const val WrongPhraseText = "Without the secret words you can’t restore access to the wallet."
    const val EnterWordsBtnTitle = "Enter 24 secret words"
    const val CreateNewWalletBtnTitle = "Create a new empty wallet instead"
    const val WalletActionImport = "import_wallet"
    const val WalletActionCreate = "create_wallet"

    const val TON_GLOBAL_CONFIG_URL = "https://ton.org/global-config.json"

    const val IS_AUTHORIZED = 1
    const val IS_NOT_AUTHORIZED = 2
    const val NOT_STATED_AUTH_STATUS = 3

    const val SOURCE_CODE = "te6cckECFAEAAtQAART/APSkE/S88sgLAQIBIAIDAgFIBAUE+PKDCNcYINMf0x/THwL4I7vyZO1E0NMf0x/T//QE0VFDuvKhUVG68qIF+QFUEGT5EPKj+AAkpMjLH1JAyx9SMMv/UhD0AMntVPgPAdMHIcAAn2xRkyDXSpbTB9QC+wDoMOAhwAHjACHAAuMAAcADkTDjDQOkyMsfEssfy/8QERITAubQAdDTAyFxsJJfBOAi10nBIJJfBOAC0x8hghBwbHVnvSKCEGRzdHK9sJJfBeAD+kAwIPpEAcjKB8v/ydDtRNCBAUDXIfQEMFyBAQj0Cm+hMbOSXwfgBdM/yCWCEHBsdWe6kjgw4w0DghBkc3RyupJfBuMNBgcCASAICQB4AfoA9AQw+CdvIjBQCqEhvvLgUIIQcGx1Z4MesXCAGFAEywUmzxZY+gIZ9ADLaRfLH1Jgyz8gyYBA+wAGAIpQBIEBCPRZMO1E0IEBQNcgyAHPFvQAye1UAXKwjiOCEGRzdHKDHrFwgBhQBcsFUAPPFiP6AhPLassfyz/JgED7AJJfA+ICASAKCwBZvSQrb2omhAgKBrkPoCGEcNQICEekk30pkQzmkD6f+YN4EoAbeBAUiYcVnzGEAgFYDA0AEbjJftRNDXCx+AA9sp37UTQgQFA1yH0BDACyMoHy//J0AGBAQj0Cm+hMYAIBIA4PABmtznaiaEAga5Drhf/AABmvHfaiaEAQa5DrhY/AAG7SB/oA1NQi+QAFyMoHFcv/ydB3dIAYyMsFywIizxZQBfoCFMtrEszMyXP7AMhAFIEBCPRR8qcCAHCBAQjXGPoA0z/IVCBHgQEI9FHyp4IQbm90ZXB0gBjIywXLAlAGzxZQBPoCFMtqEssfyz/Jc/sAAgBsgQEI1xj6ANM/MFIkgQEI9Fnyp4IQZHN0cnB0gBjIywXLAlAFzxZQA/oCE8tqyx8Syz/Jc/sAAAr0AMntVGliJeU="

    const val OUT_TRANSACTION = "OUT"
    const val IN_TRANSACTION = "IN"

    const val STATUS_TRANSACTION_SUCCESS = "success"
    const val STATUS_TRANSACTION_PROCESSING = "processing"
    const val STATUS_TRANSACTION_DENIED = "denied"

    const val NANO_NUM = 1000000000

    const val RECEIVE_BTN_TITLE = "Receive"
    const val SEND_BTN_TITLE = "Send"
    const val WAIT_WHILE_TON_CONFIG_IS_LOADING = "Wait while ton config is loading"

    const val WALLET_CREATED = "Wallet Created"
    const val YOUR_ADDRESS = "Your wallet address"

    const val TRANSACTIONS_BOTTOM_SHEET_CONTENT = 0
    const val SEND_BOTTOM_SHEET_CONTENT = 1
    const val RECEIVE_BOTTOM_SHEET_CONTENT = 2
    const val TRANSACTION_BOTTOM_SHEET_CONTENT = 3

    const val SEND_TON_TITLE = "Send TON"
    const val WALLET_ADDRESS_TITLE = "Wallet Address"
    const val ADDRESS_TEXT_FIELD_PLACEHOLDER = "Enter Wallet Address..."
    const val PASTE_ADDRESS_TITLE = "Paste the 24-letter wallet address of the recipient here."
    const val PASTE_BUTTON_TITLE = "Paste"
    const val SCAN_BUTTON_TITLE = "Scan"

    const val INVALID_ADDRESS_TITLE = "Invalid Address"
    const val ADDRESS_DOESNT_BELONG_TON_TITLE = "Address entered does not belong to TON"

    const val ENTER_ADDRESS_TRANSFER_PROGRESS = 1
    const val ENTER_AMOUNT_TRANSFER_PROGRESS = 2
    const val CONFIRM_TRANSFER_PROGRESS = 3
    const val PENDING_TRANSFER_PROGRESS = 4

    const val INSUFFICIENT_FUNDS_ERROR = "Insufficient funds"
    const val MIN_AMOUNT_ERROR = "Min amount 0.08"
    const val INCORRECT_AMOUNT = "Incorrect amount"
}

object OpCodes {
    const val OP_WITHDRAW = 2222
    const val OP_MARKET_DEPLOY_NFT_SELLER = 11
    const val OP_MARKET_DEPLOY_FT_SELLER = 12

    const val OP_MARKET_CANCEL_NFT_SALE = 31
    const val OP_MARKET_CANCEL_FT_SALE = 32

    const val OP_SELLER_INIT = 1
    const val OP_SELLER_BUY = 2
    const val OP_SELLER_CANCEL = 3

    const val OP_COLLECTION_MINT = 1
    const val OP_COLLECTION_MINT_BATCH = 2

    //    const val OP_CHANGE_OWNER = 3
    const val OP_CHANGE_CONTENT_AND_ROYALTY = 4

    const val OP_NFT_TRANSFER = 0x5fcc3d14
    const val OP_NFT_OWNERSHIP_ASSIGNED = 0x5138d91

    const val OP_SFT_MINTER_MINT = 21
    const val OP_SFT_WALLET_BURN = 0x595f07bc

    // ft tokens transfer triggered by ft wallet
    const val OP_WALLET_TRANSFER = 0xf8a7ea5

    // ft tokens initial transfer triggered by ft minter
    // or between wallets
    const val OP_WALLET_INTERTRANSFER = 0x178d4519

    const val OP_SFT_TRANSFER_NOTIFICATION = 0x7362d09c
}