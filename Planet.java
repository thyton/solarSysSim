public class Planet {
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;

    public static final double G = 6.67e-11;
    public Planet (double xP, double yP, double xV,
    double yV, double m, String img)
    {
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }

    public Planet(Planet p)
    {
        xxPos = p.xxPos;
        yyPos = p.yyPos;
        xxVel = p.xxVel;
        yyVel = p.yyVel;
        mass = p.mass;
        imgFileName = p.imgFileName;
    }

    public double calcDistance(Planet p)
    {
        return  Math.sqrt(Math.pow(p.xxPos - xxPos, 2) + Math.pow(p.yyPos - yyPos, 2)); 
    }  

    public double calcForceExertedBy(Planet p)
    {
        return G*mass*p.mass/(Math.pow(p.xxPos - xxPos, 2) + Math.pow(p.yyPos - yyPos, 2));
    }  

    public double calcForceExertedByX(Planet p)
    {
        double r = Math.sqrt(Math.pow(p.xxPos - xxPos, 2) + Math.pow(p.yyPos - yyPos, 2));
        return (p.xxPos-xxPos)*G*mass*p.mass/Math.pow(r,3);
    }

    public double calcForceExertedByY(Planet p)
    {
        double r = Math.sqrt(Math.pow(p.xxPos - xxPos, 2) + Math.pow(p.yyPos - yyPos, 2));
        return (p.yyPos-yyPos)*G*mass*p.mass/Math.pow(r,3);
    }

    public double calcNetForceExertedByX(Planet [] ps)
    {
        double net = 0;
        for(Planet p : ps)
        {
            if(!this.equals(p))
            {
                double r = Math.sqrt(Math.pow(p.xxPos - xxPos, 2) + Math.pow(p.yyPos - yyPos, 2));
                net += (p.xxPos-xxPos)*G*mass*p.mass/Math.pow(r,3);
            }
        }
        return net;
    }

    public double calcNetForceExertedByY(Planet [] ps)
    {
        double net = 0;
        for(Planet p : ps)
        {
            if(!this.equals(p))
            {
                double r = Math.sqrt(Math.pow(p.xxPos - xxPos, 2) + Math.pow(p.yyPos - yyPos, 2));
                net += (p.yyPos-yyPos)*G*mass*p.mass/Math.pow(r,3);
            }
        }
        return net;
    }

    public void update(double dt, double xForce, double yForce)
    {
        double xA = xForce/mass, yA = yForce/mass;
        xxVel += xA*dt;
        yyVel += yA*dt;
        xxPos += xxVel*dt;
        yyPos += yyVel*dt;
    }

    public void draw()
    {
        StdDraw.picture(xxPos, yyPos, "images/" + imgFileName);
        StdDraw.show();
    }
};