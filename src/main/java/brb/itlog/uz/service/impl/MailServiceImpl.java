package brb.itlog.uz.service.impl;

import brb.itlog.uz.model.dto.email.EmailSendRequestDTO;
import brb.itlog.uz.service.MailService;
import brb.itlog.uz.util.MailServiceUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class MailServiceImpl implements MailService {

    private final MailServiceUtil mailServiceUtil;

    @Override
    public String send(EmailSendRequestDTO request) {

        // ToDo check Email and GET TOKEN (Save confirmationUrl Link 1 sutka )!!!

        String confirmationUrl = "https://itblog.brb-tech.uz/members/?token=z5QjMJLrGSN-xEskTqZhMLNLW5z4ThcG&action=signup&r=https%%3A%%2F%%2Fitblog.brb-tech.uz%%2F";

        String formatText = """
                <style>
                    a.button {
                        display: inline-block;
                        color: #ffffff;
                        background-color: #c93327;
                        border: solid 1px #c93327;
                        border-radius: 5px;
                        box-sizing: border-box;
                        text-decoration: none;
                        font-size: 16px;
                        padding: 9px 22px 10px;
                        margin: 0 auto;
                    }
                </style>
                
                <div style="background-color:#ffffff;font-family:-apple-system,BlinkMacSystemFont,'Segoe UI',Roboto,Helvetica,Arial,sans-serif,'Apple Color Emoji','Segoe UI Emoji','Segoe UI Symbol';font-size:14px;line-height:1.5em;margin:0;padding:0">
                    <div style="max-width:540px;margin:0 auto;padding:30px 20px">
                        <span style="color:transparent;display:none;height:0;max-height:0;max-width:0;opacity:0;overflow:hidden;width:0">Complete signup for BRB-TECH IT BLOG!</span>
                
                        <p style="font-size:20px;color:#15212a;font-weight:bold;margin-bottom:15px">Hey there!</p>
                        <p style="font-size:16px;color:#3a464c;margin-bottom:32px">
                            BRB-TECH IT BLOG uchun ro'yxatdan o'tish jarayonini yakunlash va avtomatik ravishda tizimga kirish uchun quyidagi havolani bosing:
                        </p>
                
                        <div style="text-align:center;margin-bottom:35px">
                            <a href="%s" class="button" target="_blank">Confirm signup</a>
                        </div>
                
                        <p style="font-size:16px;color:#3a464c;margin-bottom:11px">
                            Xavfsizligingiz uchun havola 24 soat ichida yaroqsiz bo'ladi.
                        </p>
                        <p style="font-size:16px;color:#3a464c;margin-bottom:30px">Ko'rishguncha!</p>
                
                        <hr>
                
                        <p style="font-size:15px;color:#3a464c">Ushbu URL manzilini brauzeringizga nusxalashingiz va joylashtirishingiz mumkin:</p>
                        <p style="word-break:break-all;font-size:15px;color:#3a464c">
                            <a href="%s" target="_blank">%s</a>
                        </p>
                
                        <p style="font-size:11px;color:#738a94;margin-top:80px">
                            Agar siz ushbu so'rovni amalga oshirmagan bo'lsangiz, bu xabarni shunchaki o'chirib tashlashingiz mumkin. Siz ro'yxatdan o'tmaysiz va siz uchun hech qanday hisob yaratilmaydi.
                        </p>
                        <p style="font-size:11px;color:#738a94">
                            This message was sent from <a href="https://itblog.brb-tech.uz/" style="color:#738a94;text-decoration:underline" target="_blank">itblog.brb-tech.uz</a> to %s
                        </p>
                
                        <table style="width:100%%">
                            <tr>
                                <td style="font-family:-apple-system,BlinkMacSystemFont,'Segoe UI',Roboto,Helvetica,Arial,sans-serif,'Apple Color Emoji','Segoe UI Emoji','Segoe UI Symbol';font-size:14px;vertical-align:top">&nbsp;</td>
                            </tr>
                        </table>
                    </div>
                </div>
                """;

        String finalHtml = String.format(formatText, confirmationUrl, confirmationUrl, confirmationUrl, request.getEmail());

        mailServiceUtil.send(request.getEmail(), "Complete registration", finalHtml);
        log.info("This Email: " + request.getEmail() + " sent successfully in this email");
        return "Success";
    }

}
