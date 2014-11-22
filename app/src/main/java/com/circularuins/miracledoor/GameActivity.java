package com.circularuins.miracledoor;

import android.app.Activity;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;


public class GameActivity extends Activity {
    //カウンター
    private int seenCount; //シーン用
    private int textCount; //セリフ用
    //イメージファイル名
    private String[] imageNames = {"s1", "s2", "s3", "s4", "s5", "s6", "s7", "s8", "s9", "s10",
            "s11", "s12", "s13", "s14", "s15", "s16", "s17", "s18", "s19",
            "s23", "s24", "s25", "s28", "s29", "s30",
            "s31", "s33", "s32", "s34", "s35", "s36", "s37", "s38", "s39", "s40",
            "s41", "s42", "s43", "s40", "s44", "s45", "s46", "s48", "s47", "s49", "s50", "s44", "s48",
            "s81", "s83", "s81", "s82", "s48", "s87", "s89",
            "s52", "s51", "s53", "s54", "s22",
            "s84", "s85", "s86",
            "s55", "s56", "s58", "s55", "s57", "s55", "s57", "s55", "s59", "s57", "s55", "s59", "s55", "s60", "s26",
            "s61", "s55", "s62", "s63", "s64", "s63", "s64", "s34", "s64", "s63", "s64", "s63", "s64", "s63", "s64", "s63", "s64", "s63", "s64", "s26", "s27", "s80", "s79", "s63", "s32", "s33", "s65", "s80",
            "s66", "s67", "s68", "s69", "s18", "s70", "s10",
            "s72", "s73", "s74",
            "s63", "s64", "s1", "end"
    };
    private ArrayList<Integer> imageIds = new ArrayList<Integer>();
    //セリフテキスト
    private String[][] plotTexts = {
            {"ボクの名前はチンチロニャン。", "ボクのすむ山に、ずっと眠ったままの猫爺がイマス。","でも、今日、猫爺が目覚めました。"},
            {"猫爺『ここは・・・』", "チンチロ『ここは、火炎山です』", "猫爺『なんという星だ・・・』", "チンチロ『地球です』", "猫爺『やっぱりか・・・この星の因果律からは逃れられん』", "チンチロ『お爺様は、ボクが生まれた時からここで寝ていました』", "チンチロ『あなたは何者ですか？』", "猫爺『おまえはチンチロだな』", "チンチロ『え・・・ボクの名前、知ってるなんて・・・』", "猫爺『オーの奴め・・・余計なことを・・・。』", "チンチロ『猫爺様、きっと貴方はすごい人生を歩んできたに違いありません』", "チンチロ『ボクも貴方のように、輝いた人生を歩みたいです！！』", "猫爺『・・・・・・・』"},
            {"チンチロ『ワッ・・・・！！』", "その時、ボクの頭の中に、忘れていたことが、フッと浮かびあがった。"},
            {"ずっと忘れていたこと・・・・・", "ずっと・・・・・・・・・・", "ずっと・・・・・"},
            {""},
            {"・・・ボクは、昔女の子だった。"},
            {"・・・歳をとって・・・"},
            {"・・・もっと歳をとって・・・"},
            {"・・・死んだ。", "死んだ時に思った、良かった、って。", "楽しい人生だった・・・って。", "ボクに子供はできなかった。だからボクの血はここでおしまいだった。", "ただ、楽しく生きた人生・・・", "何も残らない人生・・・", "大事にしていた本も、友達も、思い出も・・・みんな消えた。", "70年を生きて死んだ。"},
            {"ボクがまだ8つの時、月に大きな隕石が衝突した。", "その影響で、月は衛星軌道を大きく外れ、地球と火星の均衡地点に留まり、新たな太陽系第4惑星の誕生によって、火星以降の順列が1つずつ繰り上がった。"},
            {"惑星「月」の存在は、小惑星群を内側へ引き寄せる要因となり、地球に光の雨を降らせた。"},
            {"隕石による多大な被害によって、政治・経済・地球環境・生態系、あらゆる状況は混乱を極め、"},
            {"更に追い討ちをかけるが如く、隕石が運んできたとされる海藤ウイルスによって、人類は窮地に追い込まれていった。"},
            {"ボクが20歳になった時・・・、人間の人格・記憶の走査と、情報管理、それらを人の脳と同様に働くプログラム上で、完全なシュミレートが可能になったと、日本の最大手CメーカーであるEIGHT・EYEが発表。"},
            {"その3年後、世界で初めての、ブレインプログラム上で起動する人間のアイデンティティ「MEGIHA」が公表された。"},
            {"更に、脳内情報の転写されていないブレインに、自我を宿らせ、一個人を創り出す技術が開発。"},
            {"その基礎理論を提唱し、初期のプロットを作り上げたトム・トレーダス博士の名を取り、そのアドバンスド・ブレイン・プログラムは、総称して「トレーダー」と呼ばれた。"},
            {"ボクが40になって結婚した頃に、人間の脳には、明らかに人工的プログラムが施されたと推測される情報を持った結線野（ラストボックスと呼称）がある事が発見された。", "この人類が、第三者からの人為的な作用によって創作または管理されている可能性が強く予想され始めた。", "46歳の時、夫だった男性と離婚した。"},
            {"その時、自分自身の転写データを〈アケローン〉で起動させた。"},
            {"そこでソリタリという名の男の子と知り合った。", "彼はまだ11歳だったが、本当の累計年数は50年くらいだという。"},
            {"ボクとソリタリは親密になった。", "ボクは、ソリタリの簡素な単語を綴ったようなシンプルな喋り方が好きになった。", "ソリタリには実体だった時の記憶が全くなかったが、どういう経緯を辿り、今に至るかを情報として知っていた。"},
            {"ソリタリは、元々は女性で、既婚者だった。", "10代の終わりに産んだ娘が海藤ウイルスに感染し、その手術費をまかなうためにソリタリの夫は犯罪に手を染め逮捕された。"},
            {"手段を失ったソリタリは、脳内データを人工被体に移植させ、元の人体を売却した。", "すでに20歳を越えているとは言え、若い女性の人体は、手術費と被体を購入するための代金には届いた。"},
            {"被体の性別が男になってしまったのは、女のそれよりも遥かに安値だったからだ。", "それでも、少しでも安値で抑えるため、かなり成長が進んだ2歳児の被体を購入した。"},
            {"それがわざわいして、記憶の再現率は最低の12.5%を下回り、ソリタリは移植前の記憶を失った。"},
            {"11歳の時、ソリタリは国から得られた最後の保護金を使って、自分のデータを〈アケローン〉で走らせることを決意した。"},
            {"その時から彼の時間は止まったままだ。実体の行方は知らないらしい。"},
            {"ボクもソリタリに自分のことをいっぱい話した。", "・・・・子供の頃のこと、結婚生活のこと、夫との離婚のこと・・・・、ボク達はすごくお互いに親しくなれた。"},
            {"ソリタリの友人は少なかった。", "彼は、訪問者の9割をランダムで拒絶するようにプログラミングしていた。", "だからか、そのトラップを掻い潜って来た彼の数人の友人には、奇妙な人物が多かった。幸運にもボクもその中の一人だったというわけだ。"},
            {"ソリタリは月に一度だけ実界と接触した。", "接触相手は、彼の実界での友人であった天野めぐみという彼の同級生だった。"},
            {"ソリタリの設定する時間と実界との時差のせいで、天野めぐみは既に16歳になっていた。ソリタリにとって彼女は実界での唯一の友人だった。", "前述しているが、移植前のソリタリには娘がいたそうだ。", "ソリタリが、移植前から引き継いだ僅かなエピソード記憶などから、彼女を娘の姿と照らしあわせてか、天野めぐみに無意識に愛情を感じているのは明らかだった。", "実界で生きる彼女の生気に溢れた姿は、データ化したボクには叶わない何かを秘めていた。", "彼女と話すソリタリの目には、普段アケローンでは見せない輝きがあることをボクは知っていた。"},
            {"ある日、ソリタリの数少ない友人の1人であるヒロコが彼を訪問した。外見は16歳くらいであろうか。", "彼女は、1000ラマで30分どうだ？と言った。ソリタリは哀しそうな目をして彼女を追い返した。", "ボク『どういう意味？』", "ソリタリ『1000ラマで30分セックスはいかが、という意味』"},
            {"ヒロコには実界に妹がいるらしい。", "ヒロコの妹は、まだ幼く、数年前に海藤ウイルスに感染した。", "ヒロコの両親は心血を流し手術費を工面したが、母親が宗教に走り、次女の治癒を信じ全額を宗教団体に寄付してしまった、", "当然だが、それでヒロコの妹の病気が治るわけもなく、元絵描きだったヒロコの父親は、心に病を負い、精神病院で首を吊って自殺した。", "母親は宗教に没頭しついぞ家に戻らなくなった。", "妹と2人残されたヒロコは、なんとか手術費を稼ぐため、昼間から路地裏に立ち体を売った。", "若過ぎたヒロコの体は、すぐに病に蝕まれ、アケローン行きを余儀なくされた。妹は施設に預けられたが、国は手術費まで面倒は見てくれない・・・・。"},
            {"ボクは、その話を聞いて、エイダとエグゼの姉弟のことを思い出した。"},
            {"ボクが実界で暮らしていた頃に、家の近くで夜遅く、2人で路地に立っていた。", "暗闇の中で、鼻水を垂らしたエグゼの手を、小さなエイダの手がしっかりと引いていた。", "彼女達には両親はいない。施設も彼女達を受け入れようとはしない。何故なら彼女達が青色人の血を引いているからだ。"},
            {"海藤ウイルスによって大量の死亡者が世界で溢れた時、そのウイルスに免疫を持つ子供達がアフリカ中央域でたくさん産まれた。", "医学会はすぐに彼らの遺伝情報を調べあげワクチンの精製に利用しようとしたが、すぐに諦めた。"},
            {"彼らの遺伝子は海藤ウイルスに対するなんの免疫力も備えていなかった。", "ただ、海藤ウイルスに対抗する手段を大量の死の中で模索していくうち、神様が見つけた方法の1つが彼らだった。", "彼らの遺伝子はテロメアゲノムが極端に短く、健康児でも一生の寿命は20年に満たない。海藤ウイルスは、平均寿命の長い生物にしか感染しなかった。"},
            {"彼らは肌の色から青色人と呼ばれ、彼らの繁栄を各国は厳しく規制した。彼らが全世界に繁栄すれば、人類の平均寿命が60年も短くなってしまうから。", "青色人の遺伝情報を持つものは、この国では何の援助も受けられない。むろん、売春行為も違法とされていた。"},
            {"しかし、まだ8歳のエイダは、4歳の弟の手を引き、体を売るために路地に立たなければ、親の居ない彼女達が公的差別の中で生き抜いていく事は不可能だった。"},
            {"ソリタリにエイダ達の話を聞かせると、彼はヒロコに見せたのと同じ哀しい目をした。"},
            {"ある日、ソリタリと対岸を歩いていると、女性が裸で波打ち際に倒れているのを発見した。", "急いで介抱しようとすると、ソリタリが止めた。", "ボク『どうして？』", "ソリタリ『知ってる人なんだ。彼女は、望んでいるんだよ』"},
            {"見るとその女性は、舌を痙攣させながら笑って『いぃいぃきもち・・・・』と呟いた。", "その女性の名は、ペドといった。", "高校生だが、快楽シナユニの「10A」（テンアー）を常時開放していた。", "快楽シナユニは、実界では、使用制限が25歳以上で、精神審査レベルA以上の者でないと許されていないし、", "通常は1周間1時間までの時間制限と、ポテンシャル制限がかかっているが、ここでは無法と同じだった。"},
            {"来た対岸を戻りながら、ソリタリに、ペドの話を聞いた。"},
            {"ペドもまた、ソリタリの10%の出会い確率を越えて、彼に接触できた数少ない人間の1人だった。", "ソリタリが初めてペドと出会った時は、彼女がアケローンに到着して僅かな頃で、まだ快楽に身を委ねていない静かな女性だったという。", "そして、ペドにアケローンを案内して行く内、ソリタリはペドに求愛した事を聞いて、ボクはショックを受けた。"},
            {"ペドは心を病んでいた。", "ペドは、物心つかぬ内に実父を亡くし、母親の再婚相手の義父に9歳の時レイプされた。", "義父との関係は中学を卒業するまで続き、卒業と同時にペドは家を出た。その後、水商売を転々とし資金を貯め、闇組織に身を染めて、18歳の時、アケローンに到着した。", "どうしてペドに惹かれたかソリタリは自分でもわからないと答えた。ペドとの付き合いはソリタリ時間で1年続き、そして終わりを迎えた。", "ソリタリと別れた直後から、ペドは快楽主義者となってしまったらしい。10Aの常時開放など、常人のすることではない。"},
            {"10Aの常時開放は、快楽シナユニの内でも特級に位するもので、24時間中、口と目と鼻と排泄器と性器から、快楽の泉が湧き出る。", "もはや、人格を維持できなくなり、永遠に、天国から抜け出せない。"},
            {"砂辺を歩く間、ソリタリは何も喋らなかった。沈黙がボクの不安を大きくしていく。", "ボク『・・・・ねぇ、今日もソリタリの家でご飯食べてもいい？』", "ボクはいつもと同じソリタリの微笑みを心の底から期待して、彼の顔をのぞきこんだ。", "ソリタリ『夜は1人で食事を取ることにしたよ。また明日』", "ボク『え、そうなんだ・・・・寂しいよ』"},
            {"寂しそうな素振りを見せれば、いつものように構ってくれると怖いくらい信じたが、ソリタリは何も言わず1人で歩いて行った。", "胸の真ん中に、灰色の鉛を埋め込まれたような重さを感じた。"},
            {"対岸を越えた辺りで、自宅への帰路の途中、ルナティックに出くわした。", "ルナティックもやはりソリタリとの対面を果たした希少なる人物の1人だったが、彼には2つの人格があった。", "頭が弱く、普段はボクの事を「おねえちゃん」と呼び良くなついた。", "ボク『こんばんわ、ルナ』", "ルナ『こーんばーんわ、おねえちゃん、うみーをみたーの？』", "ボク『そだよ、波が綺麗だったよ』", "ルナ『・・・・ほ、本当だーぁ、きれーい・・・・ソルおにーちゃんーもー・・・・いっしょーだった・・・・』", "ボク『！・・・・そっか、見たんだね、あはは、ルナは偉いね』"},
            {"ルナの脳には、特殊な視覚シナユニが取り付けられていた。どうやら、半径数キロに渡る範囲を、時間差±30分程度に渡り【見る】事ができるらしい。", "一般人が入手するには余りにレベルが過度な代物で、ルナティックがどのような経緯を経てそれを手に入れたかは、もはや彼に問うことは無意味だった。彼の知能では、さほど遠い過去は思い出せないだろう。"},
            {"そこで、ルナの人格が入れ代わった。ボクとソリタリが一緒に居た事を、彼が知ったことに対するボクの驚きに、「彼」が反応したのかもしれない。"},
            {"ルナ『・・・・ソリタリはあの女がまだ忘れられないぜ』", "ボク『・・・・！・・・・』", "ルナ『・・・・ソリタリの引き出しには、あの女とのヴィジメモリーがあるし、あいつはきっと夜な夜なそれを見て1人で楽しんでいるだろうよ！』", "ボク『ルナ・・・・嘘は止めて・・・・』", "ルナ『嘘・・・・？何言ってんだ、俺様は何でも「見れる」んだぜ？ゲヘヘへ・・・・キサマだって興奮したんだろ、ソリタリの知らない一面を見て、不安と期待が押し寄せる・・・・ぎゃはは、このメス濡れてやがる』", "ボクは、ルナティックを背に、歩き出した。", "ルナ『待てよ！おい！・・・・見てやろおか？俺はあいつの全部を見れるぜ！？ぎゃはは、残念だったな！あいつはビッチのヒロコや、色狂いのペドに夢中だ！あの毎月会ってる実界の女ともヤってるかもな！！』"},
            {"ボクは、何も考えずに全力でそこから走り去った。遠くでまだルナティックの笑い声が聞こえた。"},
            {"自室のドアの前で、自分が泣いている事に気が付いた。"},
            {"瞳を拭うと、しばらく見忘れていた涙が指を濡らせていた。それを見て、声を上げて泣いた。"},
            {"その夜ボクは、ペドとソリタリがどんな肉体関係を結んでいたかを考えた。", "そして、最初からソリタリはボクの事など何も想っていなかったのかもしれないと考え出すと、その加速はもう止まらなかった。崖を転がり落ちるように、ボクの心は落ちていった。"},
            {"次の日の夜、ソリタリの寝室へのアクセスを申請したら、ディナイされた。"},
            {"自分の愚かさと、身のほど知らずに恥ずかしくて、ソリタリとのフレンドレベルを初期化した。これでもう、もしソリタリの家の門を叩いても、90%の確率で永久に会えなくなった。"},
            {"ソリタリの対訪問者プログラムは、ランダムで9割の訪問者を拒絶し、その拒絶者のアクセス権を永久に剥奪するものである。ボクとソリタリの出会いが、ささやかな幸運の元に成り立っていたことをボクは思い知った。"},
            {"それから1年は、ボクは誰との接触も行わなかった。", "ソリタリから何らかのアクセスを期待した日もあったが、対訪問者プログラムを敷く彼のコミュニケーションプログラムが、彼側からの能動的アクセスを許可していない事は明白だった。"},
            {"ある日、夜子がボクのうちを訪ねてきた。", "夜子もまたソリタリの友人の1人であったが、ソリタリの次にボクは彼女の事を信頼していた。", "夜子『ソリタリとのフレンドレベル解いたんだってね』", "彼女の姿は1年前と何も変わってはいなかった。", "夜子『どうしてもっと素直にならない？ソリタリだって、あんたの事信じてたはずだよ』", "ボク『もう彼の話はしないで、忘れたいの。』", "夜子『忘れたいなら記憶を消去すればいい。なぜしないのさ、想い出のためか？』", "ボク『・・・・私は貴方のように記憶を簡単に捨てられないのよ、想い出だけは残したいから』", "夜子『あたしから見れば、あんたのやった事なんて想い出にすら値しないよ。何もやってないじゃないの。何もせず、あんたは消えたんだ。』", "ボク『・・・・もういいの、どうせもうソリタリとも会えないわ。』"},
            {"夜子がボクの体を押し倒し、唇に迫ってきた。", "ボク『・・・・きゃっ、いやっ！！』"},
            {"夜子と初めて会ったのは、彼女がソリタリの家を初めて訪問した時だった。", "ソリタリが名前を尋ねたが、夜子は自分の名前を知らなかった。", "彼女の履歴を調べると、彼女がこれまでに43度に渡って違う肉体や、または界に移動し続けている事が分かった。", "界への移動だけなら危険は少ないが、肉体を移動するとなると物理的に記憶の消耗は避けられなくなる。", "つまり彼女は、自分の記憶や過去を消したい・・・・という衝動を、意識的ではなく、本能に突き動かされているのだ。", "夜子という名前は、ボクとソリタリで2人で考えて名づけてあげた名前だった。", "夜子と出会った日、彼女はボクの家への宿泊を希望し、一緒に眠りについたが、真夜中に、彼女がボクの体を求めてきて驚き目が覚めた。", "何度も肉体を移動する彼女の魂には、もはや性別は無意味と化しているのかもしれない。"},
            {"2年が過ぎ、ある1人の男がボクを訪問した。", "彼の名はバズラと言った。", "12歳の子供で、彼はバズリックベイビーで、実界とアケローン界の行き来を自由に出来た。"},
            {"トレーダーの発達によって、近年、模擬生体に後から人工的な人格形成要因を注入させ、意識を生まれさせる技術が実用化された。", "彼らは、バズリックベイビーと呼ばれ、自らの出産を望まない裕福な夫婦が、子供を持つ手段として多く利用されていた。"},
            {"彼らの肉体は常に汎用的で、いかなる界ともコンタクトを可能としていた。"},
            {"バズラ『あなたは、ソリタリという人をご存知ですか？』"},
            {"彼の質問は、ボクの記憶の中の閉じ込めていた箱をいっぺんに開かせるのに十分過ぎていた。", "ボク『彼が何か？』"},
            {"バズラ『彼にアクセスを求めましたが、ディナイされ、どうも一度ディナイされると永久にアクセスが認められないトラップが敷いてあったらしく』", "ボク『そうでしょう。もうどんな事をしても彼には会えませんね』", "バズラ『いえ、外側からのアクセス手段がもう1つありました・・・・』"},
            {"ボク『嘘・・・・』"},
            {"バズラ『プログラムは例外を1つだけ用意していました。・・・・メア・キュルパの通行を自由とする・・・・そうありました。』"},
            {"・・・・メア・キュルパ・・・・ボクの名前だった。"},
            {"ボク『・・・・そんなことありえないわ！だって、あのプログラムは結線されていて、もう誰にも変更することはできなくなっているのよ！』"},
            {"バズラ『その通りです、結線プログラムはたとえアケローンの創造主ですら変える事のできぬ絶対秩序です。』"},
            {"頭が混乱して、わけがわからなかった。", "次のバズラの一言が、全てを証明していた。"},
            {"バズラ『何もおかしい事はありません。ソリタリは、プログラムの結線段階から、貴方の名前を例外として入力していただけです。』", "ボク『・・・・ソリタリと出会ったのはたった2年前よ・・・・それまでソリタリなんて人聞いたことも会ったこともない・・・・ソリタリのプログラムはもう何十年も前から結線済みなのよ！！", "・・・・彼は・・・・彼は、私と出会う何十年も前から、私の名前を知っていたってわけ？』", "12歳のバズラは、静かな眼差しでボクを見つめて、「そうでしょう」と答えた。"},
            {"バズラは機密情報局の局員だった。"},
            {"バズラは、ソリタリが移植される前の肉体だった時の、彼の、いや彼女の夫だった男の行方を探していた。", "娘の手術費のために犯罪を犯し、逮捕された彼・ガイガルは、その後、木星にある収容所に送還された。"},
            {"そこで、実験段階のシナプスユニットや人工強化組織などの人体実験の被験者として務めていたが、数年後に脱獄。", "実験時の遺産である高レベルなシナプスユニットなどを体内に残したまま逃走し、指名手配を受けていた。"},
            {"バズラ『きっとガイガルは、妻の居場所を探し当て、ソリタリの元へと辿りつくでしょう。・・・・妻だった女性が、今はアケローンで11歳の男の子になっているとも知らず。』"},
            {"ボクとバズラは、ソリタリへのアクセスを行った。", "予想通りバズラはプログラムから拒否されたが、ボクには10%通過のランダムが発生した。", "〈ディナイ〉・・・・ルーレットは外れた・・・・にも関わらず、扉はボクの通過を許可した。", "バズラ『ソリタリに会ったら、ガイガルについての情報をこのアドレスにキャプチャーして下さい。』"},
            {"ソリタリが扉の向こう側で、ボクを見つめていた。"},
            {"ボク『どうして、私の名前を知っていたの！？2年前に出会う前から、あなたは私の事を知っていたんでしょ？』"},
            {"ソリタリ『知っていたよ・・・・』"},
            {"ボク『どういうことか説明して！！』"},
            {"ソリタリは黙り込んだ。そして下をうつむいてしまった。"},
            {"ボク『2年前もそうだった・・・・』", "ボク『貴方は黙ってしまう・・・・臆病な私は怖くてなにもできなかった。』", "ボク『私・・・・貴方が好きだったの・・・・』", "ボク『だって・・・・あんなに親しくおしゃべりして、色んなことを分かち合って、だから貴方も私の事好きでいてくれてるはずだって・・・・』"},
            {"ソリタリ『・・・・』"},
            {"ボク『・・・・私ったら馬鹿みたいよね、ペドとの事聞いた途端に不安になっちゃったりして、それで、試したの・・・・私が寝室を訪ねたら迎え入れてくれるかどうか。』"},
            {"ソリタリ『・・・・ごめん』"},
            {"ボク『・・・・いいえ、私が愚かだっただけよ。貴方は私のことずっと前から知っていたんでしょう。私が実界にいる頃からよね、貴方は何なの！？私の何！？』"},
            {"ソリタリ『・・・・僕も君が好きだったんだ』"},
            {"ボク『嘘だわ』"},
            {"ソリタリ『・・・・2年前に出会った時から、君を知って、君に惹かれた。』"},
            {"ボク『嘘言わないでよ、だって貴方はそれより昔から私の名前をプログラムに入力していたのよ！』"},
            {"ソリタリ『・・・・嘘じゃない！』", "ソリタリ『前にも言った通り、僕には記憶がないんだ・・・・移植前の記憶。でも、そんな事僕の知ったことじゃない、今のこの僕が全てだ。移植前の人生なんて、他人の出来事に過ぎない』", "ソリタリ『でも、怖いんだ！移植前の事実が僕を苦しめる！！』"},
            {"ボク『どういうこと・・・・もっと分かるように説明して・・・・』"},
            {"ソリタリ『僕は移植前は女性だった！夫もいた！そして子供も！』"},
            {"ソリタリ『でも全然憶えていない！・・・・だけど情報として知っている・・・・、娘が感染して高い手術代が必用になた事・・・・、夫がそのために犯罪を犯して逮捕された事・・・・』"},
            {"ソリタリ『そして・・・・、・・・・娘の名前が、メア・キュルパだった事も知っているんだ！！！！』"},
            {"ボク『・・・・そんな・・・・』"},
            {"ソリタリ『・・・・移植した後も、僕の記憶の中には、『娘のために！』という感情記憶だけは根強く残っていた』", "ソリタリ『移植前の出来事を情報として知ってからは、尚更、その子の事が気になった。いつも心の中の小さな宿り場にしていた。』", "ソリタリ『アケローンに入る時、娘が今も幸せであることを祈って、プログラムに例外を設けさせた。』"},
            {"ソリタリ『年月が過ぎ、そして君が現れた！！』", "でも、会って改めて実感したんだ。やはり移植前の僕は僕じゃないと、実の娘と対面してもそこに親子の感情は全くないと、君は僕にとって単なる他人の女性に過ぎないと知ったんだ"},
            {"ソリタリ『そして好きになったんだ。他人の女性を好きになったんだ。・・・・自分の・・・・娘に恋してしまったんだ・・・・』"},
            {"ボク『・・・・そんな・・・・』"},
            {"ソリタリ『・・・・好きなんだ、君が！・・・・』"},
            {"その12年後、地球上に住むあらゆる【人】が死滅した。"},
            {"人間が持ちえる人格というのは、記憶や経験などを持つ脳細胞の軸索の結合状態であるネットの中におぼろげに生まれるもの、と、近代科学は長年定義し続けていたが、トレーダー博士の晩年の仮説は違っていた。"},
            {"トレーダー博士は、人格の存在場所こそ、ラストボックスであると説明していた。"},
            {"ラストボックスにある人格とは、経験や記憶の持ち主という概念ではなく、「一生命体としての証明」であり、", "ラストボックスを持ち得ることで初めて、この宇宙自身に影響を及ぼす能力を持つ知的生命体である、と博士は説いた。"},
            {"人間の脳に、人工的プログラムが施されたと推測される結線野・ラストボックスが発見された時から、このラストボックスの機能について様々な研究が行われた。"},
            {"終末期、ついにラストボックスから極微細な波が往復され続けていることが発見された。その波が、あまりに小さく磁場や重力波と混同しやすい事が、発見の遅れの原因となった。"},
            {"その波は、第4惑星である月と交信していた。そして、年々その更新頻度が衰えている理由に、半世紀前の月への隕石衝突が思い浮かばれた。"},
            {"様々な仮説が飛び交う中、人間を第72線波で覆った空間に閉じ込めると、24時間後に必ず、医学的致死原因をもたずして脳波が消失し、死亡することが立証された。", "その情報は世界を震撼させた。"},
            {"宇宙局は、ラストボックスが送受信する間隔が、永久に途絶える日付を公開していた。"},
            {"アケローンへの入植者は前年の50万倍にも跳ね上がった。月での探索が進み、核中枢付近から未知なる物質が発見された。"},
            {"アケローンの中で、ボクとソリタリは、寿命をプログラムした。"},
            {"そして、死後の転生をプログラムした。"},
            {"ボクは小さなネコに。ソリタリは何に生まれ変わっただろう。"},
            {"完"}

    };
    //プロット（シーン）管理リスト
    private ArrayList<Plot> plots = new ArrayList<Plot>();
    //BGM用
    private MediaPlayer mp;
    //効果音用変数
    private SoundPool sp;
    private int spID;
    //ウィジェット参照用
    private ImageView imageView;
    private TextView textView;
    private Button button;
    //テキストサイズをデバイス解像度に対応させるための値
    float width_ratio;
    float height_ratio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        //カウントの初期化
        seenCount = 0;
        textCount = 0;

        //イメージリソースID配列の取得
        for(String name : imageNames) {
            int id = getResources().getIdentifier(name, "drawable", getPackageName());
            imageIds.add(id);
        }
        //プロット保存用オブジェクトの初期化
        for (int i = 0; i < plotTexts.length; i++) {
            plots.add(new Plot(imageIds.get(i), plotTexts[i]));
        }
        //BGMのセット
        plots.get(105).setBgmName("love"); //106シーン目にBGMをセット

        //各ウィジェット参照の取得
        imageView = (ImageView)findViewById(R.id.seenView);
        textView = (TextView)findViewById(R.id.protView);
        button = (Button)findViewById(R.id.nextBtn);

        //最初の画面表示
        //画像の表示
        imageView.setImageResource(imageIds.get(seenCount));
        //imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        //テキストの表示
        textView.setText(plots.get(seenCount).getTextArray()[textCount]);
        textCount++; //セリフを進める

        //効果音の読み込み
        sp = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
        spID = sp.load(GameActivity.this, R.raw.walk, 1);
        button.setSoundEffectsEnabled(false); //ボタンのデフォルトクリック音をオフ

        //「奇跡の扉」ボタンアクション
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //効果音の再生
                sp.play(spID, 0.5f, 1.0f, 0, 0, 1.0f);

                //テキストサイズを解像度によって調整
                textView.setTextSize(22 * width_ratio);

                //各上限値
                final int PLOT_MAX = plots.size();
                final int TEXT_MAX = plots.get(seenCount).getTextArray().length;

                if(seenCount < PLOT_MAX) {
                    if(textCount < TEXT_MAX) {
                        //画像の表示
                        imageView.setImageResource(imageIds.get(seenCount));
                        //テキストの表示
                        textView.setText(plots.get(seenCount).getTextArray()[textCount]);
                        textCount++; //セリフを進める
                        //BGM再生
                        if(!plots.get(seenCount).getBgmName().isEmpty()) {
                            int id = getResources().getIdentifier(plots.get(seenCount).getBgmName(), "raw", getPackageName());
                            mp = MediaPlayer.create(GameActivity.this, id);
                            mp.start();
                        }
                    } else {
                        textCount = 0; //セリフを配列の頭に戻す
                        seenCount++; //シーンを進める
                        if(seenCount < PLOT_MAX) {
                            //画像の表示
                            imageView.setImageResource(imageIds.get(seenCount));
                            //テキストの表示
                            textView.setText(plots.get(seenCount).getTextArray()[textCount]);
                            textCount++; //セリフを進める
                            //BGM再生
                            if(!plots.get(seenCount).getBgmName().isEmpty()) {
                                int id = getResources().getIdentifier(plots.get(seenCount).getBgmName(), "raw", getPackageName());
                                mp = MediaPlayer.create(GameActivity.this, id);
                                mp.start();
                            }
                        } else {
                            mp.stop(); //BGM停止
                            sp.release(); //効果音開放
                            //オープニング画面に戻る
                            finish();
                        }
                    }
                }
            }
        });
    }

    //onCreateの後に呼ばれるらしい？
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);

        //テキストサイズをデバイス解像度に対応させるための値
        width_ratio = (float)(textView.getWidth() / 1200f);
        height_ratio = (float)(textView.getHeight() / 1200f);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.game, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
